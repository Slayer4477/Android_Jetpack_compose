package com.example.myapplication.domain.model

data class MoviesResult(
    val movies: List<Movie>,
    val isFromCache: Boolean,
)
