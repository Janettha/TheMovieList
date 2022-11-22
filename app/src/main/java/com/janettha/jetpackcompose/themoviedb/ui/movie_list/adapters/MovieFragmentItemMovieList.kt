package com.janettha.jetpackcompose.themoviedb.ui.movie_list.adapters

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.janettha.jetpackcompose.themoviedb.R
import com.janettha.jetpackcompose.themoviedb.databinding.ItemFragmentMovieListBinding
import com.janettha.jetpackcompose.themoviedb.domain.model.MovieModel
import com.xwray.groupie.viewbinding.BindableItem

class MovieFragmentItemMovieList(
    private val model: MovieModel
): BindableItem<ItemFragmentMovieListBinding>() {

    override fun bind(binding: ItemFragmentMovieListBinding, p1: Int) {
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

    override fun getLayout() = R.layout.item_fragment_movie_list

    override fun initializeViewBinding(p0: View) = ItemFragmentMovieListBinding.bind(p0)

}