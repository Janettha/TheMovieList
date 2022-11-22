package com.janettha.jetpackcompose.themoviedb.domain.repository

import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.FirebaseResponse

interface FirebaseRepository {

    suspend fun saveLocation(
        lat: String,
        long: String,
        currentDate: String
    ): FirebaseResponse

    suspend fun savePhoto(
        title: String,
        imageUri: String
    ): FirebaseResponse

}