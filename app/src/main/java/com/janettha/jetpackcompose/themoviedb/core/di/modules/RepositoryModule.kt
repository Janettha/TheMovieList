package com.janettha.jetpackcompose.themoviedb.core.di.modules

import com.janettha.jetpackcompose.themoviedb.data.repository.MovieRepositoryImpl
import com.janettha.jetpackcompose.themoviedb.data.repository.ProfileRepositoryImpl
import com.janettha.jetpackcompose.themoviedb.domain.repository.MovieRepository
import com.janettha.jetpackcompose.themoviedb.domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesMovieRepository(
            impl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    abstract fun providesProfileRepository(
        impl: ProfileRepositoryImpl
    ): ProfileRepository

}