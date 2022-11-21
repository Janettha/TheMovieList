package com.janettha.jetpackcompose.themoviedb.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janettha.jetpackcompose.themoviedb.core.util.extensions.asLivedata
import com.janettha.jetpackcompose.themoviedb.domain.MovieUseCases
import com.janettha.jetpackcompose.themoviedb.domain.model.PopularMovieSectionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    //private val userUseCases: UserUseCases,
    private val useCases: MovieUseCases
) : ViewModel() {

    private var apiKey: Int = 1

    init {
        getPopularMovieSection(apiKey)
    }

    // region PopularMovies
    private val _onGetPopularMovieSection: MutableLiveData<PopularMovieSectionModel> =
        MutableLiveData()
    val onGetPopularMovieSection: LiveData<PopularMovieSectionModel> =
        _onGetPopularMovieSection.asLivedata()

    private fun getPopularMovieSection(page: Int) = viewModelScope.launch {
        _onGetPopularMovieSection.value = useCases.movieListUseCase(page).data
    }
    // endregion


}