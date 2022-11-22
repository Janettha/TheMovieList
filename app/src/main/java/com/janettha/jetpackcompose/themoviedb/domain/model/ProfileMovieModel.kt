package com.janettha.jetpackcompose.themoviedb.domain.model

data class ProfileMovieModel(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val rating: Float?,
    val releaseDate: String?
)