package com.example.movie.di
import com.example.movie.domain.usecase.MoviesUseCase
import com.example.movie.domain.repo.MoviewRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
// UseCaseModule used to make hilt provide us instance from UseCase.
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideUseCase(mealsRepo: MoviewRepo): MoviesUseCase {
        return MoviesUseCase(mealsRepo)
    }


}