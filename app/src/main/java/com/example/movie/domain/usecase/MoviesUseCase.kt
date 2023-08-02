package com.example.movie.domain.usecase
import com.example.movie.domain.repo.MoviewRepo
import javax.inject.Inject
//Protocol used in MovieViewModel.
class MoviesUseCase @Inject constructor(val moviewRepo: MoviewRepo) {
    suspend operator fun invoke(queries: Map<String, String>) = moviewRepo.getMovies(queries)
}


