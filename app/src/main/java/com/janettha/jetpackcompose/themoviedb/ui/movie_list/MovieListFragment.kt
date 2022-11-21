package com.janettha.jetpackcompose.themoviedb.ui.movie_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.janettha.jetpackcompose.themoviedb.MainViewModel
import com.janettha.jetpackcompose.themoviedb.databinding.FragmentMovieListBinding
import com.janettha.jetpackcompose.themoviedb.databinding.FragmentProfileBinding
import com.janettha.jetpackcompose.themoviedb.domain.model.PopularMovieModel
import com.janettha.jetpackcompose.themoviedb.domain.model.PopularMovieSectionModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private val mTag = "ProfileFragment"

    // region COMPONENTS
    private lateinit var binding: FragmentMovieListBinding

    private val viewModel by viewModels<MainViewModel>()
    // endregion

    // region VARIABLES
    private val adapterRecyclerPopularMovieList = GroupAdapter<GroupieViewHolder>()

    private var listOfMovies: List<PopularMovieModel>? = null
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
            textViewTitle.setText("Janettha")
            /*
            recyclerViewPopularMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapterRecyclerPopularMovieList
            }
            */
        }

        subscribeObservableStreams()
    }

    private fun subscribeObservableStreams() {
        viewModel.onGetPopularMovieSection.observe(viewLifecycleOwner) { result ->
            if(result != null)
                onGetPopularMovieSection(result)
        }
    }

    // region OBSERVABLE METHODS
    private fun onGetPopularMovieSection(popularMovieSection: PopularMovieSectionModel) {
        listOfMovies = popularMovieSection.movieList
        Log.d(mTag, "onGetPopularMovieSection: ${listOfMovies!!.size}")
        //adapterRecyclerPopularMovieList.submit(listOfMovies.)
    }
    // endregion
}