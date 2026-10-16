package com.example.myapplication.data.remote

interface MovieRemoteDataSource {
    suspend fun fetchMovies(): List<MovieDto>
}
