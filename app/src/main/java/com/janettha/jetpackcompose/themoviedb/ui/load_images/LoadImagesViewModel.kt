package com.janettha.jetpackcompose.themoviedb.ui.load_images

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.FirebaseResponse
import com.janettha.jetpackcompose.themoviedb.domain.PhotoFirebaseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadImagesViewModel @Inject constructor(
    private val useCases: PhotoFirebaseUseCases,
    private val app: Application
): ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading = _isLoading
    var result = MutableLiveData<FirebaseResponse>()

    fun resetResult() {
        result = MutableLiveData<FirebaseResponse>()
    }

    fun savePhoto(title: String, imageUri: String?) =
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            try {
                if (isNetworkAvailable(app)) {
                    val apiResult =
                        useCases.postPhotoFirebaseUseCase(title, imageUri)
                    result.postValue(apiResult)
                    _isLoading.postValue(false)
                } else {
                    _isLoading.postValue(false)
                    result.postValue(FirebaseResponse.Error("Internet is not available"))
                }
            } catch (exception: Exception) {
                _isLoading.postValue(false)
                result.postValue(FirebaseResponse.Error(exception.message.toString()))
            }
        }

    private fun isNetworkAvailable(context: Context) =
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            getNetworkCapabilities(activeNetwork)?.run {
                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } ?: false
        }
}