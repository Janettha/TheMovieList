package com.janettha.jetpackcompose.themoviedb.data.datasource.web.model

import com.google.gson.annotations.SerializedName

data class SessionAuthenticationProfileResponse (
    @SerializedName("success") val success: Boolean,
    @SerializedName("session_id") val sessionId: String
)