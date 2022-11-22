package com.janettha.jetpackcompose.themoviedb.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janettha.jetpackcompose.themoviedb.core.util.extensions.asLivedata
import com.janettha.jetpackcompose.themoviedb.domain.MovieUseCases
import com.janettha.jetpackcompose.themoviedb.domain.ProfileUseCases
import com.janettha.jetpackcompose.themoviedb.domain.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val useCases: ProfileUseCases
) : ViewModel() {

    // region Token Profile
    private val _onGetTokenProfile: MutableLiveData<ProfileTokenModel> =
        MutableLiveData()
    val onGetTokenProfile: LiveData<ProfileTokenModel> =
        _onGetTokenProfile.asLivedata()

    private fun getTokenProfile() = viewModelScope.launch {
        _onGetTokenProfile.value = useCases.tokenProfileUseCase().data
        val token = _onGetTokenProfile.value!!.requestToken
    }
    // endregion

    // region Session Authentication Profile with Token
    private val _onGetSessionAuthenticationProfile: MutableLiveData<ProfileTokenModel> =
        MutableLiveData()
    val onGetSessionAuthenticationProfile: LiveData<ProfileTokenModel> =
        _onGetSessionAuthenticationProfile.asLivedata()

    fun getSessionAuthenticationProfile(token: String) = viewModelScope.launch {
        _onGetSessionAuthenticationProfile.value = useCases.sessionAuthenticationProfileUseCase(
            token
        ).data
    }
    // endregion

    // region Session Profile
    private val _onGetSessionProfile: MutableLiveData<ProfileSessionModel> =
        MutableLiveData()
    val onGetSessionProfile: LiveData<ProfileSessionModel> =
        _onGetSessionProfile.asLivedata()

    fun getSessionProfile(token: String) = viewModelScope.launch {
        _onGetSessionProfile.value = useCases.sessionProfileUseCase(
            token
        ).data
    }
    // endregion

    // region Profile Details
    private val _onGetProfileDetails: MutableLiveData<ProfileDetailsModel> =
        MutableLiveData()
    val onGetProfileDetails: LiveData<ProfileDetailsModel> =
        _onGetProfileDetails.asLivedata()

    fun getProfileDetails(sessionId: String) = viewModelScope.launch {
        _onGetProfileDetails.value = useCases.profileDetailsUseCase(
            sessionId
        ).data
    }
    // endregion

    // region Profile Rated Movies
    private val _getProfileRatedMoviesUseCase: MutableLiveData<ProfileRatedMoviesModel> =
        MutableLiveData()
    val onGetProfileRatedMoviesUseCase: LiveData<ProfileRatedMoviesModel> =
        _getProfileRatedMoviesUseCase.asLivedata()

    fun getProfileRatedMovies() = viewModelScope.launch {
        _getProfileRatedMoviesUseCase.value = useCases.profileRatedMoviesUseCase(
            sessionId = _onGetSessionProfile.value!!.sessionId,
            accountId = _onGetProfileDetails.value!!.id!!
        ).data
    }
    // endregion

    init {
        getTokenProfile()
    }

}