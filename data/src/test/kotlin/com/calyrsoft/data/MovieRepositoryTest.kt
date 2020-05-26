package com.calyrsoft.data

import com.calyrsoft.domain.Movie
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {
    @Mock
    lateinit var remoteDataSource: IRemoteDataSource

    private val apiKey = "121341234234"
    lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        moviesRepository =
            MoviesRepository(remoteDataSource, apiKey)
    }

    val mockedMovie = Movie(
        "Ad Astra",
        "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
        "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
    )

    @Test
    fun `getPopularMovies calls getPopularMovies remoteDataSource`() {
        runBlocking {
            val remoteMovies = listOf<Movie>(mockedMovie, mockedMovie.copy("joker"))
            whenever(moviesRepository.getPopularMovies()).thenReturn(remoteMovies)
            val result = moviesRepository.getPopularMovies()
            verify(remoteDataSource).getPopularMovies(any())
            //verify(remoteDataSource, times(2)).getPopularMovies(apiKey)
        }
    }

    @Test
    fun `findAllMovies calls remote data source`() {
        runBlocking {
            //GIVEN
            val movie = mockedMovie.copy(title = "Hanibal Lecter")
            val list = ArrayList<Movie>()
            list.add(movie)
            whenever(remoteDataSource.getPopularMovies(any()))
                .thenReturn(list)
            //WHEN
            val result = moviesRepository.getPopularMovies()
            //THEN
            Assert.assertEquals(list, result)
        }
    }
}