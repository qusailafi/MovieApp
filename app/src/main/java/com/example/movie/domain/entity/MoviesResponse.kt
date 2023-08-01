package com.example.movie.domain.entity

import com.google.gson.annotations.SerializedName

class MoviesResponse{
@SerializedName("page")
      var page: Int=0
    @SerializedName("results")

    var results=ArrayList<Result>()
    @SerializedName("total_pages")

    var total_pages: Int=0
    @SerializedName("total_results")

    var total_results:Int =0
}

