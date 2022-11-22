package com.janettha.jetpackcompose.themoviedb.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.janettha.jetpackcompose.themoviedb.databinding.FragmentProfileBinding
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieModel
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieSectionModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val mTag = "ProfileFragment"

    // region COMPONENTS
    private lateinit var binding: FragmentProfileBinding

    private val viewModel by viewModels<ProfileViewModel>()
    // endregion

    // region VARIABLES
    private val adapterRecyclerPopularMovieList = GroupAdapter<GroupieViewHolder>()

    private var listOfMovies: List<MovieModel>? = null
    // endregion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentProfileBinding.inflate(inflater, container, false).let {
        binding = it
        binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            textViewProfileUser.text = "Janettha"
            textViewProfileEmail.text = "aksjd@fsdf.c"
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
        viewModel.onGetTokenProfile.observe(viewLifecycleOwner) { token ->
            Log.d(mTag, "onGetTokenProfile: ${token.requestToken}")
        }

        viewModel.onGetSessionProfile.observe(viewLifecycleOwner) { session ->
            Log.d(mTag, "onGetSessionProfile: ${session.sessionId}")
        }
    }

    // region OBSERVABLE METHODS
    private fun onGetPopularMovieSection(popularMovieSection: MovieSectionModel) {
        listOfMovies = popularMovieSection.movieList
        Log.d(mTag, "onGetPopularMovieSection: ${listOfMovies!!.size}")
        //adapterRecyclerPopularMovieList.submit(listOfMovies.)
    }
    // endregion
}