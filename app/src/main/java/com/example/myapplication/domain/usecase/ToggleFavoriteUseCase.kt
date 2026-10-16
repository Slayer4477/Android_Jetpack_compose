package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.MovieRepository

class ToggleFavoriteUseCase(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(movieId: Int): Boolean {
        return repository.toggleFavorite(movieId)
    }
}
