package com.example.movie.utils

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movie.BuildConfig
import com.example.movie.R

//this file used to binding the data in views via data binding
object BindViews {
    @JvmStatic

    @BindingAdapter("imageurl")
    fun loadImage(
        view: View,
        imageUrl: String?
    ) {
        val image: ImageView = view as ImageView


        Glide.with(view.context)
            .load(BuildConfig.BASE_IMAGE_URL + "w300" + imageUrl)
            .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
            .into(image)
    }

    @JvmStatic

    @BindingAdapter("android:rating")
    fun setRating(view: RatingBar, rating: Double) {
        if (view.rating != rating.toFloat()) {
            view.rating = rating.toFloat()
        }
    }
}