package com.janettha.jetpackcompose.themoviedb.domain.model

data class MovieSectionModel (
    //val dates: List<String>,
    val page: Int,
    val movieList: List<MovieModel>,
    val totalPages: Int,
    val totalResults: Int
)