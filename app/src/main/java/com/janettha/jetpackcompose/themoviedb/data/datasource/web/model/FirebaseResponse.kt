package com.janettha.jetpackcompose.themoviedb.data.datasource.web.model

sealed class FirebaseResponse {
    data class Success(var data: Any? = null, var message: String? = null) : FirebaseResponse()
    data class Error(var message: String) : FirebaseResponse()
    object Loading: FirebaseResponse()
}