package com.example.movie.di

import com.example.movie.data.remote.ApiEndPoints
import com.example.movie.data.repo.RepoImpl
import com.example.movie.domain.repo.MoviewRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
// RepoModule used to make hilt provide us instance from MoviewRepo.
@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiEndPoints): MoviewRepo{
        return RepoImpl(apiService)
    }
}