package com.janettha.jetpackcompose.themoviedb.data.datasource.web.model

import com.google.gson.annotations.SerializedName

data class TokenAuthenticationProfileResponse (
    @SerializedName("success") val success: Boolean,
    @SerializedName("expires_at") val expiresAt: String,
    @SerializedName("request_token") val requestToken: String,
)