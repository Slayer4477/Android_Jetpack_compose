package com.example.myapplication.presentation.movies

import com.example.myapplication.domain.model.Movie

sealed interface MovieListUiState {
    data object Loading : MovieListUiState
    data class Success(
        val movies: List<Movie>,
        val isFromCache: Boolean,
    ) : MovieListUiState
    data class Error(val message: String) : MovieListUiState
}
