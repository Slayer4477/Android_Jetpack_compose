package com.example.myapplication.di

import com.example.myapplication.data.local.InMemoryMovieLocalDataSource
import com.example.myapplication.data.local.MovieLocalDataSource
import com.example.myapplication.data.remote.FakeMovieRemoteDataSource
import com.example.myapplication.data.repository.MovieRepositoryImpl
import com.example.myapplication.domain.repository.MovieRepository
import com.example.myapplication.domain.usecase.GetMoviesUseCase
import com.example.myapplication.domain.usecase.ToggleFavoriteUseCase

class AppContainer {
    val fakeRemote = FakeMovieRemoteDataSource()
    private val local: MovieLocalDataSource = InMemoryMovieLocalDataSource()
    private val repository: MovieRepository = MovieRepositoryImpl(fakeRemote, local)

    val getMovies = GetMoviesUseCase(repository)
    val toggleFavorite = ToggleFavoriteUseCase(repository)
}
