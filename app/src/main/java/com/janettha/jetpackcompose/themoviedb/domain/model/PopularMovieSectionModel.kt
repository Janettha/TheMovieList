package com.janettha.jetpackcompose.themoviedb.domain.model

data class PopularMovieSectionModel (
    val page: Int,
    val movieList: List<PopularMovieModel>,
    val totalPages: Int
)