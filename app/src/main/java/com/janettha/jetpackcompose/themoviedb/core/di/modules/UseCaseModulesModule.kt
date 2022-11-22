package com.janettha.jetpackcompose.themoviedb.core.di.modules

import com.janettha.jetpackcompose.themoviedb.domain.MovieUseCases
import com.janettha.jetpackcompose.themoviedb.domain.use_cases.GetMovieListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModulesModule {

    @Singleton
    @Provides
    fun providesLoginUseCases(
        useCase0: GetMovieListUseCase
    ): MovieUseCases {
        return MovieUseCases(useCase0)
    }

}