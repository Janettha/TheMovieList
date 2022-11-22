package com.janettha.jetpackcompose.themoviedb.ui.movie_list.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.janettha.jetpackcompose.themoviedb.R
import com.janettha.jetpackcompose.themoviedb.databinding.ItemFragmentMovieListBinding
import com.janettha.jetpackcompose.themoviedb.databinding.ItemFragmentMovieListSkeletonBinding
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieModel
import com.xwray.groupie.viewbinding.BindableItem

class MovieFragmentItemMovieListSkeleton(
    private val autoShimmer: Boolean = false
): BindableItem<ItemFragmentMovieListSkeletonBinding>() {

    override fun bind(binding: ItemFragmentMovieListSkeletonBinding, p1: Int) {
        with(binding) {
            if (autoShimmer) {
                frameLayoutImage.startShimmer()
            }
        }
    }

    override fun getLayout() = R.layout.item_fragment_movie_list_skeleton

    override fun initializeViewBinding(p0: View) = ItemFragmentMovieListSkeletonBinding.bind(p0)

}