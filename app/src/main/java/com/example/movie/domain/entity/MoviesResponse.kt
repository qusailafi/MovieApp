package com.example.movie.domain.entity
import com.google.gson.annotations.SerializedName
import kotlin.Result
data class MoviesResponse(
    val page: Int,
    val results: List<com.example.movie.domain.entity.Result>,
    val total_pages: Int,
    val total_results: Int
)