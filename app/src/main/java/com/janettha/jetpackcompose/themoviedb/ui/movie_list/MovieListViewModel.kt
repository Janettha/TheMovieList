package com.janettha.jetpackcompose.themoviedb.ui.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.janettha.jetpackcompose.themoviedb.core.util.extensions.asLivedata
import com.janettha.jetpackcompose.themoviedb.domain.MovieUseCases
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieSectionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val useCases: MovieUseCases
) : ViewModel() {

    private var page: Int = 1

    // region Popular Movies
    private val _onGetPopularMovieSection: MutableLiveData<MovieSectionModel> =
        MutableLiveData()
    val onGetPopularMovieSection: LiveData<MovieSectionModel> =
        _onGetPopularMovieSection.asLivedata()

    private fun getPopularMovieSection(page: Int) = viewModelScope.launch {
        _onGetPopularMovieSection.value = useCases.popularMovieListUseCase(page).data
    }
    // endregion

    // region Top Rated Movies
    private val _onGetTopRatedMovieSection: MutableLiveData<MovieSectionModel> =
        MutableLiveData()
    val onGetTopRatedMovieSection: LiveData<MovieSectionModel> =
        _onGetTopRatedMovieSection.asLivedata()

    private fun getTopRatedMovieSection(page: Int) = viewModelScope.launch {
        _onGetTopRatedMovieSection.value = useCases.topRatedMovieListUseCase(page).data
    }
    // endregion

    // region Recommendation Movies
    private val _onGetRecommendationMovieSection: MutableLiveData<MovieSectionModel> =
        MutableLiveData()
    val onGetRecommendationMovieSection: LiveData<MovieSectionModel> =
        _onGetRecommendationMovieSection.asLivedata()

    private fun getRecommendationMovieSection(page: Int) = viewModelScope.launch {
        _onGetRecommendationMovieSection.value = useCases.recommendationMovieListUseCase(page).data
    }
    // endregion

    init {
        getPopularMovieSection(page)
        getTopRatedMovieSection(page)
        getRecommendationMovieSection(page)
    }
}