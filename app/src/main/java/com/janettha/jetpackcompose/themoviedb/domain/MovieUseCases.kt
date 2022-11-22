package com.janettha.jetpackcompose.themoviedb.domain

import com.janettha.jetpackcompose.themoviedb.domain.use_cases.GetPopularMovieListUseCase
import com.janettha.jetpackcompose.themoviedb.domain.use_cases.GetRecommendationMovieListUseCase
import com.janettha.jetpackcompose.themoviedb.domain.use_cases.GetTopRatedMovieListUseCase

data class MovieUseCases (
    val popularMovieListUseCase: GetPopularMovieListUseCase,
    val topRatedMovieListUseCase: GetTopRatedMovieListUseCase,
    val recommendationMovieListUseCase: GetRecommendationMovieListUseCase
)