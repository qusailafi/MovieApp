package com.example.movie.data.remote

import com.example.movie.domain.entity.MoviesResponse
import retrofit2.Response
import retrofit2.http.*
//ApiEndPoints its a interface  will contain the Web Services.
interface ApiEndPoints {
    @GET("discover/movie")
    suspend fun getMovies(  @QueryMap map: Map<String,String>): Response<MoviesResponse>// Its a function to get movies from a Web service
   
}