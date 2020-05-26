package com.calyrsoft.cleanmovieapp

import com.calyrsoft.cleanmovieapp.ui.MainActivity
import com.calyrsoft.cleanmovieapp.ui.MainAdapter
import com.calyrsoft.cleanmovieapp.ui.MainViewModel
import com.calyrsoft.data.IRemoteDataSource
import com.calyrsoft.data.MoviesRepository
import com.calyrsoft.framework.RetrofitBuilder
import com.calyrsoft.framework.server.MovieDataSource
import com.calyrsoft.usecases.GetPopularMovie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun AndroidApplication.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, frameworkModule, scopeModule))
    }
}

private val appModule = module {
    single(named("apiKey")) { androidApplication().getString(R.string.api_key) }
    single<CoroutineDispatcher> { Dispatchers.Main}
    factory<IRemoteDataSource> { MovieDataSource(get())  }
}

val dataModule = module {
    factory { MoviesRepository(get(), get(named("apiKey")))  }
}

private val frameworkModule = module {
    factory { RetrofitBuilder }
}

private val scopeModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get()) }
        scoped { GetPopularMovie(get()) }
    }
}