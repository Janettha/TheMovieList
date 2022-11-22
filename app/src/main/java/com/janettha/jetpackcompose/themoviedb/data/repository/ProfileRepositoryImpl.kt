package com.janettha.jetpackcompose.themoviedb.data.repository

import com.janettha.jetpackcompose.themoviedb.core.data.Resource
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.ProfileWebService
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.dto.request.RequestToken
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.ProfileDetailsResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.ProfileRatedMoviesResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.SessionAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.TokenAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val remote: ProfileWebService
) : ProfileRepository {

    override suspend fun getTokenAuthenticationProfile()
            : Resource<TokenAuthenticationProfileResponse?> = executeApiRequestAsResource {
        remote.getNewToken()
    }

    override suspend fun getSessionAuthenticationProfile(requestToken: String)
            : Resource<TokenAuthenticationProfileResponse?> = executeApiRequestAsResource {
        remote.getNewSessionWithLogin(body = HashMap<String, Any>().apply {
            put("username", "janettha")
            put("password", "riptideJan23#$")
            put("request_token", requestToken)
        }
        )
    }

    override suspend fun getSessionProfile(requestToken: String)
            : Resource<SessionAuthenticationProfileResponse?> = executeApiRequestAsResource {
        remote.getNewSession(body = HashMap<String, Any>().apply {
            put("request_token", requestToken)
        }
        )
    }

    override suspend fun getProfileDetails(sessionId: String)
            : Resource<ProfileDetailsResponse?> = executeApiRequestAsResource {
        remote.getProfileDetails(sessionId = sessionId)
    }

    override suspend fun getProfileRatedMovies(sessionId: String, accountId: Int)
            : Resource<ProfileRatedMoviesResponse?> = executeApiRequestAsResource {
        remote.getProfileRatedMovies(
            sessionId = sessionId,
            //accountId = accountId
        )
    }
}