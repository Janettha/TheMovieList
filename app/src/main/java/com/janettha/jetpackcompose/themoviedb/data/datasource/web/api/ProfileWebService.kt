package com.janettha.jetpackcompose.themoviedb.data.datasource.web.api

import com.janettha.jetpackcompose.themoviedb.BuildConfig
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.dto.request.RequestToken
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.SessionAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.TokenAuthenticationProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

@JvmSuppressWildcards
interface ProfileWebService {

    companion object {

        const val CONTROLLER = "authentication"

    }

    @GET("${CONTROLLER}/token/new")
    suspend fun getNewToken (
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Response<TokenAuthenticationProfileResponse>

    @GET("${CONTROLLER}/session/new")
    suspend fun getNewSession (
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Body body: RequestToken
    ): Response<SessionAuthenticationProfileResponse>

}