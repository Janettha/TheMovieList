package com.janettha.jetpackcompose.themoviedb.data.repository

import com.janettha.jetpackcompose.themoviedb.core.data.Resource
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.ProfileWebService
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.dto.request.RequestToken
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.SessionAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.TokenAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val remote: ProfileWebService
): ProfileRepository {

    override suspend fun getTokenAuthenticationProfile()
    : Resource<TokenAuthenticationProfileResponse?> = executeApiRequestAsResource {
        remote.getNewToken()
    }

    override suspend fun getSessionAuthenticationProfile(requestToken: String)
    : Resource<SessionAuthenticationProfileResponse?> = executeApiRequestAsResource {
        remote.getNewSession(body = RequestToken(requestToken))
    }

}