package com.janettha.jetpackcompose.themoviedb.domain

import com.janettha.jetpackcompose.themoviedb.domain.use_cases.GetMovieListUseCase

data class MovieUseCases (
    val movieListUseCase: GetMovieListUseCase
)