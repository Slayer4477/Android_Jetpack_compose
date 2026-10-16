package com.example.myapplication.data.repository

import com.example.myapplication.data.local.MovieLocalDataSource
import com.example.myapplication.data.mapper.toDomain
import com.example.myapplication.data.remote.MovieRemoteDataSource
import com.example.myapplication.domain.model.MoviesResult
import com.example.myapplication.domain.repository.MovieRepository
import java.io.IOException

class MovieRepositoryImpl(
    private val remote: MovieRemoteDataSource,
    private val local: MovieLocalDataSource,
) : MovieRepository {

    override suspend fun getMovies(): MoviesResult {
        val favoriteIds = local.getFavoriteIds()
        try {
            val dtos = remote.fetchMovies()
            local.saveMovies(dtos)
            val movies = dtos.map { it.toDomain(favoriteIds.contains(it.id)) }
            return MoviesResult(movies, isFromCache = false)
        } catch (e: IOException) {
            val cached = local.getCachedMovies() ?: throw e
            val movies = cached.map { it.toDomain(favoriteIds.contains(it.id)) }
            return MoviesResult(movies, isFromCache = true)
        }
    }

    override suspend fun toggleFavorite(movieId: Int): Boolean {
        val favoriteIds = local.getFavoriteIds()
        val isFavorite = favoriteIds.contains(movieId)
        val newFavorite = !isFavorite
        local.setFavorite(movieId, newFavorite)
        return newFavorite
    }
}
