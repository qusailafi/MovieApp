package com.example.movie.domain.repo

import com.example.movie.utils.Resource
import com.example.movie.domain.entity.MoviesResponse
import kotlinx.coroutines.flow.Flow
//Protocols movie related to be called in RepoImpl.
interface MoviewRepo {
    suspend fun getMovies(  queries:Map<String,String> ): Flow<Resource<MoviesResponse>>

}