package com.janettha.jetpackcompose.themoviedb.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.janettha.jetpackcompose.themoviedb.R
import com.janettha.jetpackcompose.themoviedb.core.util.extensions.submit
import com.janettha.jetpackcompose.themoviedb.databinding.FragmentProfileBinding
import com.janettha.jetpackcompose.themoviedb.domain.model.*
import com.janettha.jetpackcompose.themoviedb.ui.movie_list.adapters.MovieFragmentItemMovieListSkeleton
import com.janettha.jetpackcompose.themoviedb.ui.profile.adapters.RatedMovieFragmentItemProfile
import com.janettha.jetpackcompose.themoviedb.ui.profile.adapters.RatedMovieFragmentItemProfileSkeleton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private val mTag = "ProfileFragment"

    // region COMPONENTS
    private lateinit var binding: FragmentProfileBinding

    private val viewModel by viewModels<ProfileViewModel>()
    // endregion

    // region VARIABLES
    private val adapterRecyclerRatedMovieList = GroupAdapter<GroupieViewHolder>()

    private var listOfMovies: List<ProfileMovieModel>? = null
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
            recyclerViewRatedMovies.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapterRecyclerRatedMovieList
            }
        }
        loadAdapterSkeleton()
        subscribeObservableStreams()
    }

    private fun subscribeObservableStreams() {
        viewModel.onGetTokenProfile.observe(viewLifecycleOwner) { token ->
            Log.d(mTag, "onGetTokenProfile: ${token.requestToken}")
            onGetTokenProfile(token)
        }

        viewModel.onGetSessionAuthenticationProfile.observe(viewLifecycleOwner) { session ->
            Log.d(mTag, "onGetSessionAuthenticationProfile: ${session.requestToken}")
            onGetSessionAuthenticationProfile(session)
        }

        viewModel.onGetSessionProfile.observe(viewLifecycleOwner) { session ->
            Log.d(mTag, "onGetSessionProfile: ${session.sessionId}")
            onGetSessionProfile(session)
        }

        viewModel.onGetProfileDetails.observe(viewLifecycleOwner) { session ->
            Log.d(mTag, "onGetProfileDetails: ${session.username}")
            onGetProfileDetails(session)
        }

        viewModel.onGetProfileRatedMoviesUseCase.observe(viewLifecycleOwner) { result ->
            onGetProfileRatedMoviesUseCase(result)
        }
    }

    // region OBSERVABLE METHODS
    private fun loadAdapterSkeleton() {
        adapterRecyclerRatedMovieList.submit(
            listOf(
                RatedMovieFragmentItemProfileSkeleton(),
                RatedMovieFragmentItemProfileSkeleton(),
                RatedMovieFragmentItemProfileSkeleton()
            )
        )
    }

    private fun onGetTokenProfile(token: ProfileTokenModel?) {
        Log.d(mTag, "onGetTokenProfile: ${token!!.requestToken}")
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getSessionAuthenticationProfile(token.requestToken)
        }
    }

    private fun onGetSessionAuthenticationProfile(session: ProfileTokenModel?) {
        Log.d(mTag, "onGetSessionProfile: ${session!!.success}")
        Log.d(mTag, "onGetSessionProfile: ${session.requestToken}")
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getSessionProfile(session.requestToken)
        }
    }

    private fun onGetSessionProfile(session: ProfileSessionModel?) {
        Log.d(mTag, "onGetSessionProfile: ${session!!.success}")
        Log.d(mTag, "onGetSessionProfile: ${session.sessionId}")
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getProfileDetails(session.sessionId)
        }
    }

    private fun onGetProfileDetails(profile: ProfileDetailsModel?) {
        Log.d(mTag, "onGetProfileDetails: ${profile!!.id}")
        Log.d(mTag, "onGetProfileDetails: ${profile.name}")
        Log.d(mTag, "onGetProfileDetails: ${profile.username}")
        Log.d(mTag, "onGetProfileDetails: ${profile.includeAdult}")
        binding.textViewProfileUser.text = profile.username
        Glide.with(binding.root)
            .load("https://image.tmdb.org/t/p/w342${profile.path}")
            .placeholder(R.drawable.ic_picture_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .circleCrop()
            .into(binding.imageViewProfile)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getProfileRatedMovies()
        }
    }

    private fun onGetProfileRatedMoviesUseCase(result: ProfileRatedMoviesModel?) {
        Log.d(mTag, "getProfileRatedMoviesUseCase: ${result!!.movieList.size}")
        listOfMovies = result.movieList
        adapterRecyclerRatedMovieList.submit(
            listOfMovies!!.map {
                RatedMovieFragmentItemProfile(it)
            },
            refresh = false,
            animate = true
        )
        adapterRecyclerRatedMovieList.notifyDataSetChanged()
    }
    // endregion
}