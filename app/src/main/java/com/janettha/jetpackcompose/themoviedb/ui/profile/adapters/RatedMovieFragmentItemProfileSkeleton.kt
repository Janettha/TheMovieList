package com.janettha.jetpackcompose.themoviedb.ui.profile.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.janettha.jetpackcompose.themoviedb.R
import com.janettha.jetpackcompose.themoviedb.databinding.ItemFragmentMovieListBinding
import com.janettha.jetpackcompose.themoviedb.databinding.ItemFragmentMovieListSkeletonBinding
import com.janettha.jetpackcompose.themoviedb.databinding.ItemFragmentProfileMovieReviewsBinding
import com.janettha.jetpackcompose.themoviedb.databinding.ItemFragmentProfileMovieReviewsSkeletonBinding
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieModel
import com.janettha.jetpackcompose.themoviedb.domain.model.ProfileMovieModel
import com.xwray.groupie.viewbinding.BindableItem

class RatedMovieFragmentItemProfileSkeleton (
    private val autoShimmer: Boolean = false
): BindableItem<ItemFragmentProfileMovieReviewsSkeletonBinding>() {

    override fun bind(binding: ItemFragmentProfileMovieReviewsSkeletonBinding, p1: Int) {
        with(binding) {
            if (autoShimmer) {
                imageViewMovie.startShimmer()
                textViewTitle.startShimmer()
                textViewDetails.startShimmer()
                textViewScore.startShimmer()
                imageViewStarScore.startShimmer()
            }
        }
    }

    override fun getLayout() = R.layout.item_fragment_profile_movie_reviews_skeleton

    override fun initializeViewBinding(p0: View) = ItemFragmentProfileMovieReviewsSkeletonBinding.bind(p0)

}