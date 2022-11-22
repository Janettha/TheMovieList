package com.janettha.jetpackcompose.themoviedb.core.util.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.FailureFirebaseResponse

fun <T> MutableLiveData<T>.asLivedata(): LiveData<T> {
    return this
}

fun <L : LiveData<FailureFirebaseResponse>> LifecycleOwner.failureFirebase(
    liveData: L,
    body: (FailureFirebaseResponse?) -> Unit
) = liveData.observe(this, Observer(body))