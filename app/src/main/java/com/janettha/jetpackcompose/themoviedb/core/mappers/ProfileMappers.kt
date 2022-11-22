package com.janettha.jetpackcompose.themoviedb.core.mappers

import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.ProfileDetailsResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.ProfileRatedMoviesResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.SessionAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.TokenAuthenticationProfileResponse
import com.janettha.jetpackcompose.themoviedb.domain.model.*

fun TokenAuthenticationProfileResponse.toModel(): ProfileTokenModel {
    return ProfileTokenModel(success, expiresAt, requestToken)
}

fun SessionAuthenticationProfileResponse.toModel(): ProfileSessionModel {
    return ProfileSessionModel(success, sessionId)
}

fun ProfileDetailsResponse.toModel(): ProfileDetailsModel {
    return ProfileDetailsModel(name, id, username, includeAdult, avatar?.tmdb?.avatarPath)
}

fun ProfileRatedMoviesResponse.toModel(): ProfileRatedMoviesModel {
    return ProfileRatedMoviesModel(
        page!!,
        movieList = results.map {
            ProfileMovieModel(
                it.id,
                it.title,
                it.overview,
                it.posterPath,
                it.backdropPath,
                it.rating?.toFloat(),
                it.releaseDate
            )
        },
        totalPages!!,
        totalResults!!
    )
}