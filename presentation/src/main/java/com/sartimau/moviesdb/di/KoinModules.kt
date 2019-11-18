package com.sartimau.moviesdb.di

import com.sartimau.domain.usecases.GetMovieByCategoryUseCase
import com.sartimau.moviesdb.viewmodels.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { MoviesViewModel(GetMovieByCategoryUseCase()) }
}