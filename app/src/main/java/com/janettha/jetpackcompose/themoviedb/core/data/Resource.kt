package com.janettha.jetpackcompose.themoviedb.core.data

sealed class Resource<T>(val data: T? = null, val error: UiText? = null) {

    class Success<T>(data: T?) : Resource<T>(data)

    class Error<T>(error: UiText, data: T? = null) : Resource<T>(data, error)

}

suspend fun <T, R> Resource<T?>.map(mapper: suspend (Resource<T?>) -> R?): Resource<R?> {
    return when (this) {
        is Resource.Error -> Resource.Error(this.error ?: UiText.emptyError(), mapper(this))

        is Resource.Success -> Resource.Success(mapper(this))
    }
}