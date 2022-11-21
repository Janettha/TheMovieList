package com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.config.interceptor

import com.janettha.jetpackcompose.themoviedb.sys.config.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", Constants.Web.API_KEY)
                .build()

        val requestBuilder = original.newBuilder().url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}