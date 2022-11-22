package com.janettha.jetpackcompose.themoviedb.domain.repository

import com.janettha.jetpackcompose.themoviedb.core.data.Resource
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.ProfileDetailsResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.ProfileRatedMoviesResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.SessionAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.TokenAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.domain.repository.abstraction.AppRepository

interface ProfileRepository : AppRepository {

    suspend fun getTokenAuthenticationProfile(): Resource<TokenAuthenticationProfileResponse?>

    suspend fun getSessionAuthenticationProfile(requestToken: String): Resource<TokenAuthenticationProfileResponse?>

    suspend fun getSessionProfile(requestToken: String): Resource<SessionAuthenticationProfileResponse?>

    suspend fun getProfileDetails(sessionId: String): Resource<ProfileDetailsResponse?>

    suspend fun getProfileRatedMovies(getProfileRatedMovies: String, accountId: Int): Resource<ProfileRatedMoviesResponse?>

}