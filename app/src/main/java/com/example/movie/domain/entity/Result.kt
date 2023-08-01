package com.example.movie.domain.entity

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.movie.BuildConfig
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Result:Serializable {
    @SerializedName("adult")
    var adult: Boolean = false

    @SerializedName("backdrop_path")
    val backdrop_path: String = ""

    @SerializedName("genre_ids")
    var genre_ids = ArrayList<Int>()

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("original_language")

    var original_language: String = ""

    @SerializedName("original_title")

    val original_title: String = ""

    @SerializedName("overview")

    var overview: String = ""

    @SerializedName("popularity")

    val popularity: Double = 0.0

    @SerializedName("poster_path")

    var poster_path: String = ""
    @SerializedName("release_date")

    var release_date: String=""
    @SerializedName("title")

    var title: String=""
    @SerializedName("video")

    var video: Boolean=false
    @SerializedName("vote_average")

    var vote_average: Double=0.0
    @SerializedName("vote_count")

    var vote_count: Int=0
}