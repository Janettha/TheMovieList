package com.janettha.jetpackcompose.themoviedb.core.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.janettha.jetpackcompose.themoviedb.domain.LocationUseCases
import com.janettha.jetpackcompose.themoviedb.domain.repository.FirebaseRepository
import com.janettha.jetpackcompose.themoviedb.domain.PhotoFirebaseUseCases
import com.janettha.jetpackcompose.themoviedb.domain.use_cases.LocationUseCase
import com.janettha.jetpackcompose.themoviedb.domain.use_cases.PhotoFirebaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseDB() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage() = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun providePhotoFirebaseUseCases(repository: FirebaseRepository): PhotoFirebaseUseCases {
        return PhotoFirebaseUseCases(
            postPhotoFirebaseUseCase = PhotoFirebaseUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideLocationFirebaseUseCases(repository: FirebaseRepository): LocationUseCases {
        return LocationUseCases(
            locationUseCase = LocationUseCase(repository)
        )
    }

}