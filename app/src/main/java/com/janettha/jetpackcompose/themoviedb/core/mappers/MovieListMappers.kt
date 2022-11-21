package com.janettha.jetpackcompose.themoviedb.core.mappers

import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.PopularMovieResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.PopularMovieSectionResponse
import com.janettha.jetpackcompose.themoviedb.domain.model.PopularMovieModel
import com.janettha.jetpackcompose.themoviedb.domain.model.PopularMovieSectionModel

fun PopularMovieSectionResponse.toModel(): PopularMovieSectionModel {
    return PopularMovieSectionModel(
            page = 1,
            if(movieList.isNullOrEmpty().not()) {
                movieList.map { it.toModel() }
            } else { emptyList() },
            totalPages = 1
    )
}

fun PopularMovieResponse.toModel(): PopularMovieModel {
    return PopularMovieModel(
        id, title, overview, posterPath, backdropPath, rating, releaseDate
    )
}