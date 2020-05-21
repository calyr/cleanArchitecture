package com.calyrsoft.usecases

import com.calyrsoft.data.MoviesRepository
import com.calyrsoft.domain.Movie

class GetPopularMovie(val moviesRepository: MoviesRepository) {
    suspend fun invoke(): List<Movie> = moviesRepository.getPopularMovies()
}
