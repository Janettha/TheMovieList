package com.janettha.jetpackcompose.themoviedb.domain.use_cases

import com.janettha.jetpackcompose.themoviedb.core.data.Resource
import com.janettha.jetpackcompose.themoviedb.core.data.map
import com.janettha.jetpackcompose.themoviedb.core.di.modules.IoDispatcher
import com.janettha.jetpackcompose.themoviedb.core.mappers.toModel
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieSectionModel
import com.janettha.jetpackcompose.themoviedb.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPopularMovieListUseCase @Inject constructor(
    private val repository: MovieRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    private val mTag = "GetPopularMovieListUseCase"

    suspend operator fun invoke(page: Int): Resource<MovieSectionModel?> = withContext(dispatcher) {
        repository.getPopularMovieList(page).map { res ->
            when (res) {
                is Resource.Error -> null

                is Resource.Success -> res.data?.toModel()
            }

        }
    }
}