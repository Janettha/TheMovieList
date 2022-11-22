package com.janettha.jetpackcompose.themoviedb.domain.model

data class ProfileTokenModel(
    val success: Boolean,
    val expiredAt: String,
    val requestToken: String
)