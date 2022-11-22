package com.janettha.jetpackcompose.themoviedb.data.datasource.web.apiimport com.janettha.jetpackcompose.themoviedb.BuildConfigimport com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.MovieSectionResponseimport retrofit2.http.GETimport retrofit2.http.Queryimport retrofit2.Response@JvmSuppressWildcardsinterface MovieWebService {    companion object {        const val CONTROLLER = "movie"    }    @GET("$CONTROLLER/popular")    suspend fun getPopularMovieList (        @Query("api_key") apiKey: String = BuildConfig.API_KEY,        @Query("page") page: Int    ): Response<MovieSectionResponse>    @GET("$CONTROLLER/top_rated")    suspend fun getTopRatingMovieList (        @Query("api_key") apiKey: String = BuildConfig.API_KEY,        @Query("page") page: Int    ): Response<MovieSectionResponse>    @GET("$CONTROLLER/upcoming")    suspend fun getRecommendationMovieList (        @Query("api_key") apiKey: String = BuildConfig.API_KEY,        @Query("page") page: Int    ): Response<MovieSectionResponse>}