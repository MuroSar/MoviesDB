package com.sartimau.domain.usecases

import com.sartimau.domain.repositories.MoviesRepository
import com.sartimau.domain.utils.Constants.CATEGORY_POPULAR
import com.sartimau.domain.utils.Constants.CATEGORY_TOP_RATED
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetMovieUseCase: KoinComponent {
    private val moviesRepository: MoviesRepository by inject()

    operator fun invoke(page: Int, category: String, networkAvailable: Boolean) = when(category) {
        CATEGORY_POPULAR -> moviesRepository.getPopularMovies(page, networkAvailable)
        CATEGORY_TOP_RATED -> moviesRepository.getTopRatedMovies(page, networkAvailable)
        else -> moviesRepository.getUpcomingMovies(page, networkAvailable)
    }

}