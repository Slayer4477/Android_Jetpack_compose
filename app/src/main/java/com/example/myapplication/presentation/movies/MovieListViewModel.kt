package com.example.myapplication.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.usecase.GetMoviesUseCase
import com.example.myapplication.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MovieListViewModel(
    private val getMovies: GetMoviesUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<MovieListUiState>(MovieListUiState.Loading)
    val state: StateFlow<MovieListUiState> = _state.asStateFlow()

    init {
        load()
    }

    fun onRefresh() {
        load()
    }

    fun onFavoriteClick(movieId: Int) {
        viewModelScope.launch {
            toggleFavorite(movieId)
            val currentState = _state.value
            if (currentState is MovieListUiState.Success) {
                val updatedMovies = currentState.movies.map { movie ->
                    if (movie.id == movieId) {
                        movie.copy(isFavorite = !movie.isFavorite)
                    } else {
                        movie
                    }
                }
                _state.value = currentState.copy(movies = updatedMovies)
            }
        }
    }

    private fun load() {
        _state.value = MovieListUiState.Loading
        viewModelScope.launch {
            try {
                val result = getMovies()
                _state.value = MovieListUiState.Success(result.movies, result.isFromCache)
            } catch (e: IOException) {
                _state.value = MovieListUiState.Error("Can't load movies. Check your connection and try again.")
            }
        }
    }
}
