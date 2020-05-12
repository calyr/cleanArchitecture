package com.calyrsoft.cleanmovieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.calyrsoft.cleanmovieapp.R
import com.calyrsoft.data.MoviesRepository
import com.calyrsoft.domain.Movie
import com.calyrsoft.framework.ApiService
import com.calyrsoft.framework.RetrofitBuilder
import com.calyrsoft.framework.server.MovieDataSource
import com.calyrsoft.usecases.GetPopularMovie
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val api = RetrofitBuilder.apiService
//        GlobalScope.launch(Dispatchers.IO) {
//
//            val result  = api.listPopularMovies("fa3e844ce31744388e07fa47c7c5d8c3")
//            Log.d("ROBERTO", Gson().toJson(result))
//            Log.d("ROBERTO", Gson().toJson(result.results))
//
//        }

        val layoutManager = GridLayoutManager(this,3)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = layoutManager

        mainViewModel = MainViewModel(GetPopularMovie(MoviesRepository(MovieDataSource( RetrofitBuilder ), "fa3e844ce31744388e07fa47c7c5d8c3")))
        mainViewModel.model.observe(this, Observer(::updateUi))

        mainViewModel.loadMovies(R.string.api_key.toString())

    }

    private fun updateUi(model: MainViewModel.UiModel?) {
        when ( model) {
            is MainViewModel.UiModel.Content -> showList(model.movies)
        }
    }

    fun showList(list: List<Movie>) {
        recycler.adapter = MainAdapter(list)
    }
}
