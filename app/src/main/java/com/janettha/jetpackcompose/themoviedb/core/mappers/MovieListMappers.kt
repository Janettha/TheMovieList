package com.janettha.jetpackcompose.themoviedb.core.mappers

import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.MovieResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.MovieSectionResponse
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieModel
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieSectionModel

fun MovieSectionResponse.toModel(): MovieSectionModel {
    return MovieSectionModel(
        page = 1,
        movieList = if (movieList.isNullOrEmpty().not()) {
            movieList.map { it.toModel() }
        } else {
            emptyList()
        },
        totalPages = 1,
        totalResults = 1
    )
}

fun MovieResponse.toModel(): MovieModel {
    return MovieModel(
        id, title, overview, posterPath, backdropPath, rating, releaseDate
    )
}