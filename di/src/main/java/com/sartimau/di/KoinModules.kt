package com.sartimau.di

import com.sartimau.data.database.MovieDatabase
import com.sartimau.data.repositories.MoviesRepositoryImpl
import com.sartimau.data.service.MovieService
import com.sartimau.domain.repositories.MoviesRepository
import com.sartimau.domain.usecases.GetMovieUseCase
import org.koin.dsl.module

val repositoriesModule = module {
    single { MovieService() }
    single { MovieDatabase() }
    single<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}

val useCasesModule = module {
    single { GetMovieUseCase() }
}