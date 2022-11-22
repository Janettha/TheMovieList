package com.janettha.jetpackcompose.themoviedb.ui.movie_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.janettha.jetpackcompose.themoviedb.core.util.extensions.submit
import com.janettha.jetpackcompose.themoviedb.databinding.FragmentMovieListBinding
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieModel
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieSectionModel
import com.janettha.jetpackcompose.themoviedb.ui.movie_list.adapters.MovieFragmentItemMovieList
import com.janettha.jetpackcompose.themoviedb.ui.movie_list.adapters.MovieFragmentItemMovieListSkeleton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private val mTag = "ProfileFragment"

    // region COMPONENTS
    private lateinit var binding: FragmentMovieListBinding

    private val viewModel by viewModels<MovieListViewModel>()
    // endregion

    // region VARIABLES
    private val adapterRecyclerPopularMovieList = GroupAdapter<GroupieViewHolder>()
    private val adapterRecyclerTopRatingMovieList = GroupAdapter<GroupieViewHolder>()
    private val adapterRecyclerRecommendationMovieList = GroupAdapter<GroupieViewHolder>()

    private var listOfMovies: List<MovieModel>? = null
    // endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentMovieListBinding.inflate(inflater, container, false).let {
        binding = it
        binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerViewPopularMovies.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = adapterRecyclerPopularMovieList
            }

            recyclerViewTopRatingMovies.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = adapterRecyclerTopRatingMovieList
            }

            recyclerViewRecommendationMovies.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                setHasFixedSize(true)
                adapter = adapterRecyclerRecommendationMovieList
            }
        }

        loadAdapterSkeleton()

        subscribeObservableStreams()
    }

    // region PRIVATE
    private fun subscribeObservableStreams() {
        viewModel.onGetPopularMovieSection.observe(viewLifecycleOwner) { result ->
            if (result != null)
                onGetPopularMovieSection(result)
        }
        viewModel.onGetTopRatedMovieSection.observe(viewLifecycleOwner) { result ->
            if (result != null)
                onGetTopRatedMovieSection(result)
        }
        viewModel.onGetRecommendationMovieSection.observe(viewLifecycleOwner) { result ->
            if (result != null)
                onGetRecommendationMovieSection(result)
        }
    }

    private fun loadAdapterSkeleton(){
        adapterRecyclerPopularMovieList.submit(
            listOf(
                MovieFragmentItemMovieListSkeleton(),
                MovieFragmentItemMovieListSkeleton(),
                MovieFragmentItemMovieListSkeleton()
            )
        )
        adapterRecyclerTopRatingMovieList.submit(
            listOf(
                MovieFragmentItemMovieListSkeleton(),
                MovieFragmentItemMovieListSkeleton(),
                MovieFragmentItemMovieListSkeleton()
            )
        )
        adapterRecyclerRecommendationMovieList.submit(
            listOf(
                MovieFragmentItemMovieListSkeleton(),
                MovieFragmentItemMovieListSkeleton(),
                MovieFragmentItemMovieListSkeleton()
            )
        )
    }
    // endregion

    // region OBSERVABLE METHODS
    private fun onGetPopularMovieSection(movieSection: MovieSectionModel) {
        listOfMovies = movieSection.movieList
        Log.d(mTag, "onGetPopularMovieSection: ${listOfMovies!!.size}")
        adapterRecyclerPopularMovieList.submit(
            listOfMovies!!.map {
                MovieFragmentItemMovieList(it)
            },
            refresh = false,
            animate = true
        )
    }

    private fun onGetTopRatedMovieSection(movieSection: MovieSectionModel) {
        listOfMovies = movieSection.movieList
        Log.d(mTag, "onGetTopRatedMovieSection: ${listOfMovies!!.size}")
        adapterRecyclerTopRatingMovieList.submit(
            listOfMovies!!.map {
                MovieFragmentItemMovieList(it)
            },
            refresh = false,
            animate = true
        )
    }

    private fun onGetRecommendationMovieSection(movieSection: MovieSectionModel) {
        listOfMovies = movieSection.movieList
        Log.d(mTag, "onGetRecommendationMovieSection: ${listOfMovies!!.size}")
        adapterRecyclerRecommendationMovieList.submit(
            listOfMovies!!.map {
                MovieFragmentItemMovieList(it)
            },
            refresh = false,
            animate = true
        )
    }
    // endregion
}