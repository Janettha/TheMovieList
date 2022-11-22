package com.janettha.jetpackcompose.themoviedb.data.datasource.web.model

sealed class FailureFirebaseResponse {
    object NetworkConnection : FailureFirebaseResponse()
    object ServerError : FailureFirebaseResponse()
    object DatabaseError : FailureFirebaseResponse()
    abstract class FeatureFailure : FailureFirebaseResponse()
}