package com.janettha.jetpackcompose.themoviedb.core.di.modules

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.janettha.jetpackcompose.themoviedb.data.repository.FirebaseRepositoryImpl
import com.janettha.jetpackcompose.themoviedb.domain.repository.FirebaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryFirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseRepository(
        db: FirebaseFirestore,
        storage: FirebaseStorage,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): FirebaseRepository {
        return FirebaseRepositoryImpl(
            db,
            storage,
            dispatcher
        )
    }

}