package com.janettha.jetpackcompose.themoviedb.data.repository

import android.net.Uri
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.janettha.jetpackcompose.themoviedb.core.di.modules.IoDispatcher
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.dto.request.LocationFirebase
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.FirebaseResponse
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.dto.request.SnapShot
import com.janettha.jetpackcompose.themoviedb.domain.repository.FirebaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepositoryImpl @Inject constructor(
    db: FirebaseFirestore,
    storageInstance: FirebaseStorage,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FirebaseRepository {

    // region VARIABLES
    // SaveLocation
    private val locationCollectionReference = db.collection("location")

    // SavePhoto
    private val storageReference = storageInstance.reference
    private val userPhotosCollectionReference = db.collection("users")
    // endregion

    override suspend fun saveLocation(
        lat: String,
        long: String,
        currentDate: String
    ): FirebaseResponse {
        return try {
            withContext(Dispatchers.IO) {
                val collectionReference = locationCollectionReference.add(
                    LocationFirebase(
                        lat,
                        long,
                        currentDate
                    )
                ).await()
                FirebaseResponse.Success(collectionReference)
            }
        } catch (e: Exception) {
            FirebaseResponse.Error(e.message.toString())
        }
    }

    override suspend fun savePhoto(
        title: String,
        imageUri: String,
    ): FirebaseResponse {
        return try {
            withContext(dispatcher) {
                val uri = Uri.parse(imageUri)
                val filePath = storageReference
                    .child("images")
                    .child("theMovieApp_byUser_${Timestamp.now().seconds}")
                filePath.putFile(uri).await()

                val docRef = userPhotosCollectionReference.add(
                    SnapShot(
                        "1",
                        title,
                        filePath.downloadUrl.await().toString()
                    )
                ).await()

                FirebaseResponse.Success(docRef)
            }
        } catch (e: Exception) {
            FirebaseResponse.Error(e.message.toString())
        }
    }

}