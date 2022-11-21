package com.janettha.jetpackcompose.themoviedb.core.data

import android.icu.number.Scale.none
import androidx.annotation.StringRes
import com.janettha.jetpackcompose.themoviedb.R

sealed class UiText {

    data class String(val text: kotlin.String) : UiText()

    data class Resource(@StringRes val res: Int) : UiText()

    companion object {

        fun unknownError(): UiText = Resource(R.string.error_unknown)

        fun emptyError(): UiText = Resource(R.string.none)

    }

}