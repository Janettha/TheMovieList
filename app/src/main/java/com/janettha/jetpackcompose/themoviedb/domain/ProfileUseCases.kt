package com.janettha.jetpackcompose.themoviedb.domain

import com.janettha.jetpackcompose.themoviedb.domain.use_cases.*

class ProfileUseCases (
    val tokenProfileUseCase: GetTokenProfileUseCase,
    val sessionAuthenticationProfileUseCase: GetSessionAuthenticationProfileUseCase,
    val sessionProfileUseCase: GetSessionProfileUseCase,
    val profileDetailsUseCase: GetProfileDetailsUseCase,
    val profileRatedMoviesUseCase: GetProfileRatedMoviesUseCase
)