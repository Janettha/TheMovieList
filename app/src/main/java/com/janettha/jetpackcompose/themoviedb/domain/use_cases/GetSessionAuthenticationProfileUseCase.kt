package com.janettha.jetpackcompose.themoviedb.domain.use_cases

import com.janettha.jetpackcompose.themoviedb.core.data.Resource
import com.janettha.jetpackcompose.themoviedb.core.data.map
import com.janettha.jetpackcompose.themoviedb.core.di.modules.IoDispatcher
import com.janettha.jetpackcompose.themoviedb.core.mappers.toModel
import com.janettha.jetpackcompose.themoviedb.domain.model.ProfileSessionModel
import com.janettha.jetpackcompose.themoviedb.domain.model.ProfileTokenModel
import com.janettha.jetpackcompose.themoviedb.domain.repository.ProfileRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSessionAuthenticationProfileUseCase @Inject constructor(
    private val repository: ProfileRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(requestToken: String): Resource<ProfileTokenModel?> =
        withContext(dispatcher) {
            repository.getSessionAuthenticationProfile(requestToken).map { res ->
                when (res) {
                    is Resource.Error -> null

                    is Resource.Success -> res.data?.toModel()
                }

            }
        }
}