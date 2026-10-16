package com.example.myapplication.data.local

import com.example.myapplication.data.remote.MovieDto

interface MovieLocalDataSource {
    suspend fun getCachedMovies(): List<MovieDto>?
    suspend fun saveMovies(movies: List<MovieDto>)
    suspend fun getFavoriteIds(): Set<Int>
    suspend fun setFavorite(id: Int, favorite: Boolean)
}
