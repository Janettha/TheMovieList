package com.janettha.jetpackcompose.themoviedb.domain.repository.abstraction

import com.janettha.jetpackcompose.themoviedb.R
import com.janettha.jetpackcompose.themoviedb.core.data.Resource
import com.janettha.jetpackcompose.themoviedb.core.data.UiText
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.config.ApiException
import retrofit2.Response
import java.net.SocketTimeoutException

interface AppRepository {

    suspend fun <T> resourceOf(
        execute: suspend () -> T?,
        onException: (suspend () -> Unit)? = null
    ): Resource<T?> {
        return try {
            Resource.Success(execute())
        } catch (e: ApiException.ApiServerException) {
            onException?.invoke()
            Resource.Error(e.message?.let { msg -> UiText.String(msg) } ?: UiText.unknownError())
        } catch (e: ApiException.ApiNoInternetException) {
            onException?.invoke()
            Resource.Error(UiText.Resource(R.string.check_your_internet_connection))
        } catch (e: ApiException.ApiInternalException) {
            onException?.invoke()
            Resource.Error(e.message?.let { msg -> UiText.String(msg) } ?: UiText.unknownError())
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun <T> executeApiRequest(apiRequest: suspend () -> Response<T>): T? {
        return try {
            with(apiRequest()) {
                if (isSuccessful.not()) {
                    throw ApiException.ApiServerException(code(), errorBody()?.string())
                }

                body()
            }
        } catch (e: SocketTimeoutException) {
            throw ApiException.ApiNoInternetException(e.message, e)
        } catch (e: Exception) {
            throw ApiException.ApiInternalException(e.message, e)
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun <T> executeApiRequestAsResource(apiRequest: suspend () -> Response<T>): Resource<T?> {
        return resourceOf(execute = {
            try {
                with(apiRequest()) {
                    if (isSuccessful.not()) {
                        throw ApiException.ApiServerException(code(), errorBody()?.string())
                    }

                    body()
                }
            } catch (e: SocketTimeoutException) {
                throw ApiException.ApiNoInternetException(e.message, e)
            } catch (e: Exception) {
                throw ApiException.ApiInternalException(e.message, e)
            }
        })
    }

}