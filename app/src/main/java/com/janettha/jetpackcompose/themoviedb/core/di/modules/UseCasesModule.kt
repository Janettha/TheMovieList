package com.janettha.jetpackcompose.themoviedb.core.di.modules

import com.janettha.jetpackcompose.themoviedb.domain.repository.MovieRepository
import com.janettha.jetpackcompose.themoviedb.domain.use_cases.GetMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(ViewModelComponent::class)
object UseCasesModule {

    @Singleton
    @Provides
    fun providesGetPopularMovieListUseCase(
            repo0: MovieRepository,
            @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetMovieListUseCase {
        return GetMovieListUseCase(repo0, dispatcher)
    }

}