package com.calyrsoft.cleanmovieapp

import com.calyrsoft.data.IRemoteDataSource
import com.calyrsoft.testshared.mockedMovie
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun initMockedDi(vararg modules: Module) {
    startKoin {
        modules( listOf( mockedAppModule, dataModule) + modules)
    }
}

private val mockedAppModule = module {
    single(named("apiKey")) { "12456" }
    single<IRemoteDataSource> { FakeRemoteDataSource() }
    single { Dispatchers.Unconfined }
}

val defaultFakeMovies = listOf(
    mockedMovie.copy("Movie1"),
    mockedMovie.copy("Movie2"),
    mockedMovie.copy("Movie3"),
    mockedMovie.copy("Movie4")
)

class FakeRemoteDataSource : IRemoteDataSource {

    var movies = defaultFakeMovies

    override suspend fun getPopularMovies(apiKey: String) = movies
}