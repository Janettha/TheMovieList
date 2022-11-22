package com.janettha.jetpackcompose.themoviedb.data.datasource.web.api

import com.janettha.jetpackcompose.themoviedb.BuildConfig
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.ProfileDetailsResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.ProfileRatedMoviesResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.SessionAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.TokenAuthenticationProfileResponse
import retrofit2.Response
import retrofit2.http.*

@JvmSuppressWildcards
interface ProfileWebService {

    companion object {

        const val CONTROLLER = "authentication"

    }

    @GET("${CONTROLLER}/token/new")
    suspend fun getNewToken (
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): Response<TokenAuthenticationProfileResponse>

    @POST("${CONTROLLER}/token/validate_with_login")
    suspend fun getNewSessionWithLogin (
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Body body: Map<String, Any>
    ): Response<TokenAuthenticationProfileResponse>

    @POST("$CONTROLLER/session/new")
    suspend fun getNewSession (
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Body body: Map<String, Any>
    ): Response<SessionAuthenticationProfileResponse>

    @GET("account")
    suspend fun getProfileDetails (
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("session_id") sessionId: String
    ): Response<ProfileDetailsResponse>

    @GET("account/{account_id}/rated/movies")
    suspend fun getProfileRatedMovies (
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("session_id") sessionId: String,
        //@Path("account_id", encoded = false) accountId: Int
    ): Response<ProfileRatedMoviesResponse>

}

