package com.janettha.jetpackcompose.themoviedb.core.di

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.MovieWebService
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.config.ApiBuilder
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.config.HttpClient
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.config.interceptor.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import javax.inject.Qualifier
import javax.inject.Singleton


@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object WebServiceModule {

    @Singleton
    @Provides
    fun providesAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache? {
        val cacheSize : Long = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson? {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @HttpClientInstance
    @Singleton
    @Provides
    fun providesHttpClient(
            authInterceptor: AuthInterceptor
    ): OkHttpClient {
        return HttpClient(authInterceptor).client
    }

    @Singleton
    @Provides
    fun providesApiBuilder(
            @HttpClientInstance okHttpClient: OkHttpClient
    ): ApiBuilder {
        return ApiBuilder(okHttpClient)
    }

    @Singleton
    @Provides
    fun providesMovieApi(
            apiBuilder: ApiBuilder
    ): MovieWebService {
        return apiBuilder.build(MovieWebService::class.java)
    }
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class HttpClientInstance