package com.janettha.jetpackcompose.themoviedb.data.datasource.web.modelimport com.google.gson.annotations.SerializedNamedata class MovieSectionResponse (        @SerializedName("page") val page: Int,        @SerializedName("results") val movieList: List<MovieResponse>,        @SerializedName("total_pages") val pages: Int,        @SerializedName("total_results") val listSize: Int)