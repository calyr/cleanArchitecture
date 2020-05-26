package com.calyrsoft.cleanmovieapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.calyrsoft.domain.Movie
import com.calyrsoft.usecases.GetPopularMovie
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    val mockedMovie = Movie(
        "Ad Astra",
        "The near future, a time when both hope and hardships drive humanity to look to the stars and beyond. While a mysterious phenomenon menaces to destroy life on planet Earth, astronaut Roy McBride undertakes a mission across the immensity of space and its many perils to uncover the truth about a lost expedition that decades before boldly faced emptiness and silence in search of the unknown.",
        "/xBHvZcjRiWyobQ9kxBhO6B2dtRI.jpg"
    )
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    lateinit var getPopularMovies: GetPopularMovie

    @Mock
    lateinit var observer: Observer<MainViewModel.UiModel>

    private lateinit var vm: MainViewModel
    @Before
    fun setUp() {
        vm = MainViewModel(getPopularMovies, Dispatchers.Unconfined)
    }

    @Test
    fun showPopularMovies() {
        runBlocking {
            val movies = listOf(mockedMovie)
            whenever(getPopularMovies.invoke()).thenReturn(movies)
            vm.model.observeForever(observer)
            vm.loadMovies()

            verify(observer).onChanged(MainViewModel.UiModel.Content(movies))
        }
    }


}