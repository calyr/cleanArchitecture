package com.calyrsoft.cleanmovieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calyrsoft.domain.Movie
import com.calyrsoft.movieapp.common.ScopedViewModel
import com.calyrsoft.usecases.GetPopularMovie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val popularMovies: GetPopularMovie ) : ScopedViewModel() {
    init {
        initScope()
    }
    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = _model

    sealed class UiModel() {
        class Content(val movies: List<Movie>) : UiModel()
    }

    fun loadMovies() {
        launch {
            _model.value = UiModel.Content(popularMovies.invoke())
        }

    }
}

