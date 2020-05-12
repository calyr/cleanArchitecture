package com.calyrsoft.framework

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie?short_by=popularity.desc")
    fun listPopularMovies(@Query("api_key") apiKey: String) : Deferred<MovieResponse>
}