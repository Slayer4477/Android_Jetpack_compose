package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.Movie
import com.example.myapplication.domain.model.MoviesResult
import com.example.myapplication.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(): MoviesResult {
        val result = repository.getMovies()
        val sorted = result.movies.sortedWith(
            compareByDescending<Movie> { it.rating }
                .thenBy { it.title }
        )
        return MoviesResult(sorted, result.isFromCache)
    }
}
