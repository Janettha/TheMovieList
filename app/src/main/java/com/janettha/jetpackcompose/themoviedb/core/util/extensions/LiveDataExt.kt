package com.janettha.jetpackcompose.themoviedb.core.util.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.asLivedata(): LiveData<T> {
    return this
}