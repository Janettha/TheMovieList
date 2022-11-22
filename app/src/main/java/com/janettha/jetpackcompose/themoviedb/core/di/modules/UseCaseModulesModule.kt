package com.janettha.jetpackcompose.themoviedb.core.di.modules

import com.janettha.jetpackcompose.themoviedb.domain.MovieUseCases
import com.janettha.jetpackcompose.themoviedb.domain.ProfileUseCases
import com.janettha.jetpackcompose.themoviedb.domain.use_cases.*
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
    fun providesMovieUseCases(
        useCase0: GetPopularMovieListUseCase,
        useCase1: GetTopRatedMovieListUseCase,
        useCase2: GetRecommendationMovieListUseCase
    ): MovieUseCases {
        return MovieUseCases(useCase0, useCase1, useCase2)
    }

    @Singleton
    @Provides
    fun providesProfileUseCases(
        useCase0: GetTokenProfileUseCase,
        useCase1: GetSessionProfileUseCase
    ): ProfileUseCases {
        return ProfileUseCases(useCase0, useCase1)
    }

}