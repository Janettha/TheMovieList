package com.janettha.jetpackcompose.themoviedb.ui.location

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.FailureFirebaseResponse
import com.janettha.jetpackcompose.themoviedb.domain.LocationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackLocationViewModel @Inject constructor(
    private val locationCases: LocationUseCases,
    private val app: Application
) : ViewModel() {

    private val _failure: MutableLiveData<FailureFirebaseResponse> = MutableLiveData()
    val failure: LiveData<FailureFirebaseResponse> = _failure

    private fun handleFailure(failure: FailureFirebaseResponse) {
        _failure.value = failure
    }

}