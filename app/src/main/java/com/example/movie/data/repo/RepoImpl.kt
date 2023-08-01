package com.example.movie.data.repo

import com.example.movie.utils.Resource
import com.example.movie.data.remote.ApiEndPoints
import com.example.movie.domain.entity.MoviesResponse
import com.example.movie.domain.repo.BaseDataSource
import com.example.movie.domain.repo.MoviewRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
//RepoImpl Class its a Reposetory that calls the apis in ApiEndPoints.
class RepoImpl(val apiEndPoints: ApiEndPoints) : MoviewRepo, BaseDataSource() {
    //
    override suspend fun getMovies(queries:Map<String,String> ): Flow<Resource<MoviesResponse>> =
        flow {
            emit(safeApiCall { apiEndPoints.getMovies( queries) })

        }




}