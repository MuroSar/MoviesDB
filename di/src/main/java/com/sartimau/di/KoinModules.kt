package com.sartimau.di

import com.sartimau.domain.usecases.GetMovieByCategoryUseCase
import org.koin.dsl.module

val repositoriesModule = module {

}

val useCasesModule = module {
    single { GetMovieByCategoryUseCase() }
}