package com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.config

import com.janettha.jetpackcompose.themoviedb.sys.config.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiBuilder @Inject constructor(
        private val okHttpClient: OkHttpClient
) {

    fun <T> build(api: Class<T>): T {
        return Retrofit.Builder()
                .baseUrl(Constants.Web.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(api)
    }

}