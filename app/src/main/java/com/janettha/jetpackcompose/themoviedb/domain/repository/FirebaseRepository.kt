package com.janettha.jetpackcompose.themoviedb.domain.repository

import com.janettha.jetpackcompose.themoviedb.core.di.modules.IoDispatcher
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.FirebaseResponse
import kotlinx.coroutines.CoroutineDispatcher

interface FirebaseRepository {
    suspend fun savePhoto(
        title: String,
        imageUri: String
    ): FirebaseResponse
}