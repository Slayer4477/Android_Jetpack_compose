package com.example.myapplication.data.mapper

import com.example.myapplication.data.remote.MovieDto
import com.example.myapplication.domain.model.Movie

fun MovieDto.toDomain(isFavorite: Boolean): Movie {
    return Movie(
        id = id,
        title = title,
        year = releaseYear,
        rating = score,
        isFavorite = isFavorite,
    )
}
