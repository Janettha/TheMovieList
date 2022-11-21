package com.janettha.jetpackcompose.themoviedb.data.datasource.web.api.config

sealed class ApiException(
        message: String?,
        throwable: Throwable?,
        code: Int? = null
) : Exception(message, throwable) {

    class ApiInternalException(
            message: String? = null,
            throwable: Throwable? = null
    ) : ApiException(message, throwable)

    class ApiServerException(
            code: Int?,
            message: String? = null,
            throwable: Throwable? = null
    ) : ApiException(code = code, message = message, throwable = throwable)

    class ApiNoInternetException(
            message: String? = null,
            throwable: Throwable? = null
    ) : ApiException(message, throwable)

}