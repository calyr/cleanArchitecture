package com.calyrsoft.cleanmovieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.calyrsoft.cleanmovieapp.R
import com.calyrsoft.domain.Movie
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by currentScope.viewModel(this)
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

        //mainViewModel = MainViewModel(GetPopularMovie(MoviesRepository(MovieDataSource( RetrofitBuilder ), "fa3e844ce31744388e07fa47c7c5d8c3")))
        mainViewModel.model.observe(this, Observer(::updateUi))

        mainViewModel.loadMovies()

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

