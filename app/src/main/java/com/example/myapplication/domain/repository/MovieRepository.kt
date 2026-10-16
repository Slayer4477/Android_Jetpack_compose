package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.MoviesResult

interface MovieRepository {
    suspend fun getMovies(): MoviesResult
    suspend fun toggleFavorite(movieId: Int): Boolean
}
