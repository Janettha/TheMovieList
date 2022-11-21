package com.janettha.jetpackcompose.themoviedb.core.util

import androidx.annotation.StringRes
import com.janettha.jetpackcompose.themoviedb.R

sealed class TextResource {

    data class String(val text: kotlin.String) : TextResource()

    data class Resource(@StringRes val res: Int) : TextResource()

    companion object {

        fun unknownError(): TextResource = Resource(R.string.error_unknown)

    }

}