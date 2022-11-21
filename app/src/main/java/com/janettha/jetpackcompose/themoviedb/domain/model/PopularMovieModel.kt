package com.janettha.jetpackcompose.themoviedb.domain.model

data class PopularMovieModel (
        val id: Long,
        val title: String,
        val overview: String,
        val posterPath: String,
        val backdropPath: String,
        val rating: Float,
        val releaseDate: String
)