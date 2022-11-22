package com.janettha.jetpackcompose.themoviedb.domain

import com.janettha.jetpackcompose.themoviedb.domain.use_cases.GetSessionProfileUseCase
import com.janettha.jetpackcompose.themoviedb.domain.use_cases.GetTokenProfileUseCase

class ProfileUseCases (
    val tokenProfileUseCase: GetTokenProfileUseCase,
    val sessionProfileUseCase: GetSessionProfileUseCase
)