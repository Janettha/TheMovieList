package com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.config

import com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.config.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

class HttpClient @Inject constructor(
        private val authInterceptor: AuthInterceptor
) {

    val client: OkHttpClient
        get() = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .addInterceptor(authInterceptor)
                .build()

}