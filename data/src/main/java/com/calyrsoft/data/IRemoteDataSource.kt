package com.calyrsoft.data

import com.calyrsoft.domain.Movie

interface IRemoteDataSource {
    suspend fun getPopularMovies(apiKey: String): List<Movie>
}