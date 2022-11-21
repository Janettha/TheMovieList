package com.janettha.jetpackcompose.themoviedb.domain.use_cases

import com.janettha.jetpackcompose.themoviedb.R
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.FirebaseResponse
import com.janettha.jetpackcompose.themoviedb.domain.repository.FirebaseRepository
import javax.inject.Inject

class PhotoFirebaseUseCase @Inject constructor(
    val repository: FirebaseRepository
) {
    suspend operator fun invoke(
        title: String,
        imageUri: String?
    ): FirebaseResponse {
        return when {
            isDataValid(title, imageUri) -> {
                repository.savePhoto(title, imageUri!!)
            }
            imageUri == null -> {
                FirebaseResponse.Error(R.string.select_a_photo.toString())
            }
            else -> {
                FirebaseResponse.Error(R.string.fields_cannot_be_empty.toString())
            }
        }
    }

    private fun isDataValid(title: String, imageUri: String?) =
        title.trim().isNotEmpty() && (imageUri != null)

}