package com.calyrsoft.usecases

import com.calyrsoft.data.MoviesRepository
import com.calyrsoft.domain.Movie
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPopularMovieTest {
    val mockedMovie = Movie(
        "Ad Astra",
        "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
        "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
    )
    @Mock
    lateinit var moviesRepository: MoviesRepository

    lateinit var getPopularMovie: GetPopularMovie
    @Before
    fun setUp() {
        getPopularMovie = GetPopularMovie(moviesRepository)
    }

    @Test
    fun `invoke calls movies repository`() {
        runBlocking {

            val movies = listOf<Movie>(mockedMovie.copy("Cartel"))
            whenever(moviesRepository.getPopularMovies()).thenReturn(movies)
            val result = getPopularMovie.invoke()
            Assert.assertEquals(movies, result)
        }
    }
}