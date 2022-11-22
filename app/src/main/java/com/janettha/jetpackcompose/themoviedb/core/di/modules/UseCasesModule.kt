package com.janettha.jetpackcompose.themoviedb.core.di.modules

import com.janettha.jetpackcompose.themoviedb.domain.repository.MovieRepository
import com.janettha.jetpackcompose.themoviedb.domain.repository.ProfileRepository
import com.janettha.jetpackcompose.themoviedb.domain.use_cases.*
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

    // region Movie
    @Singleton
    @Provides
    fun providesGetPopularMovieListUseCase(
            repo0: MovieRepository,
            @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetPopularMovieListUseCase {
        return GetPopularMovieListUseCase(repo0, dispatcher)
    }

    @Singleton
    @Provides
    fun providesGetTopRattedMovieListUseCase(
        repo0: MovieRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetTopRatedMovieListUseCase {
        return GetTopRatedMovieListUseCase(repo0, dispatcher)
    }

    @Singleton
    @Provides
    fun provideGetRecommendationMovieListUseCase(
        repo0: MovieRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetRecommendationMovieListUseCase {
        return GetRecommendationMovieListUseCase(repo0, dispatcher)
    }
    // endregion

    // region
    @Singleton
    @Provides
    fun provideGetTokenAuthenticationProfileUseCase(
        repo0: ProfileRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetTokenProfileUseCase {
        return GetTokenProfileUseCase(repo0, dispatcher)
    }

    @Singleton
    @Provides
    fun provideGetSessionAuthenticationProfileUseCase(
        repo0: ProfileRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): GetSessionProfileUseCase {
        return GetSessionProfileUseCase(repo0, dispatcher)
    }
    // endregion

}