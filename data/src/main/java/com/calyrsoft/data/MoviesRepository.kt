package com.calyrsoft.data

import com.calyrsoft.domain.Movie

class MoviesRepository(val remoteDataSource: IRemoteDataSource, val apiKey: String) {
    suspend fun getPopularMovies(): List<Movie> = remoteDataSource.getPopularMovies(apiKey)
}
