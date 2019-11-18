package com.sartimau.domain.usecases

import com.sartimau.domain.repositories.MoviesRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetMovieByCategoryUseCase: KoinComponent {
    private val moviesRepository: MoviesRepository by inject()

    operator fun invoke(category: String) = moviesRepository.getMoviesByCategory(category)

}