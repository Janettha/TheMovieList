package com.janettha.jetpackcompose.themoviedb.ui.profile.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.janettha.jetpackcompose.themoviedb.R
import com.janettha.jetpackcompose.themoviedb.databinding.ItemFragmentMovieListBinding
import com.janettha.jetpackcompose.themoviedb.databinding.ItemFragmentProfileMovieReviewsBinding
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieModel
import com.janettha.jetpackcompose.themoviedb.domain.model.ProfileMovieModel
import com.xwray.groupie.viewbinding.BindableItem

class RatedMovieFragmentItemProfile (
    private val model: ProfileMovieModel
): BindableItem<ItemFragmentProfileMovieReviewsBinding>() {

    override fun bind(binding: ItemFragmentProfileMovieReviewsBinding, p1: Int) {
        binding.textViewTitle.text = model.title
        binding.textViewDetails.text = model.overview
        binding.textViewScore.text = model.rating.toString()
        binding.root.context.let { context ->
            context.resources.getIdentifier(model.posterPath, "drawable", context.opPackageName)
        }.also { _ ->
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w342${model.posterPath}")
                .placeholder(R.drawable.ic_picture_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(binding.imageViewMovie)
        }
    }

    override fun getLayout() = R.layout.item_fragment_profile_movie_reviews

    override fun initializeViewBinding(p0: View) = ItemFragmentProfileMovieReviewsBinding.bind(p0)

}