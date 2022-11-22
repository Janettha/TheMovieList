package com.janettha.jetpackcompose.themoviedb.domain.model

data class ProfileRatedMoviesModel (
    val page: Int,
    val movieList: List<ProfileMovieModel>,
    val totalPages: Int,
    val totalResults: Int
)