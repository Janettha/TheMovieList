package com.janettha.jetpackcompose.themoviedb.ui.profile.adapters

import android.view.View
import androidx.databinding.Bindable
import com.janettha.jetpackcompose.themoviedb.databinding.ItemFragmentMovieListBinding
import com.janettha.jetpackcompose.themoviedb.domain.model.PopularMovieModel
import com.xwray.groupie.viewbinding.BindableItem

class PopularMovieFragmentItemProfile(
    private val model: PopularMovieModel
): BindableItem<ItemFragmentMovieListBinding>() {

    override fun bind(binding: ItemFragmentMovieListBinding, p1: Int) {
        //binding.model = model
    }

    override fun getLayout(): Int {
        TODO("Not yet implemented")
    }

    override fun initializeViewBinding(p0: View): ItemFragmentMovieListBinding {
        TODO("Not yet implemented")
    }

}