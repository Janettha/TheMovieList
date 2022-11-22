package com.janettha.jetpackcompose.themoviedb.core.mappers

import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.SessionAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.TokenAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.domain.model.ProfileSessionModel
import com.janettha.jetpackcompose.themoviedb.domain.model.ProfileTokenModel

fun TokenAuthenticationProfileResponse.toModel(): ProfileTokenModel {
    return ProfileTokenModel(success, expiresAt, requestToken)
}

fun SessionAuthenticationProfileResponse.toModel(): ProfileSessionModel {
    return ProfileSessionModel(success, sessionId)
}