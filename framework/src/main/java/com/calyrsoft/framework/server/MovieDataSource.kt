package com.calyrsoft.framework.server

import com.calyrsoft.data.IRemoteDataSource
import com.calyrsoft.domain.Movie
import com.calyrsoft.framework.RetrofitBuilder
import com.calyrsoft.framework.toDomainMovie

class MovieDataSource(val apiRest: RetrofitBuilder) : IRemoteDataSource {
    override suspend fun getPopularMovies(apiKey: String): List<Movie> {
        val  response = apiRest.apiService.listPopularMovies(apiKey).results.map { it.toDomainMovie() }
        response.forEach {
            println(it)
        }
        return response
    }
}