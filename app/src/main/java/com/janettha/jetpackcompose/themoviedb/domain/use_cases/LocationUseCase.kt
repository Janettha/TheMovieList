package com.janettha.jetpackcompose.themoviedb.domain.use_cases

import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.FirebaseResponse
import com.janettha.jetpackcompose.themoviedb.domain.repository.FirebaseRepository
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(
        lat: String,
        long: String,
        currentDate: String
    ): FirebaseResponse {
        return when {
            isDataValidLocation(lat, long) -> {
                firebaseRepository.saveLocation(lat, long, currentDate)
            }
            else -> {
                FirebaseResponse.Error("Fields cannot be empty")
            }
        }
    }

    private fun isDataValidLocation(title: String, imageUri: String?) =
        title.trim().isNotEmpty() && (imageUri != null)

}