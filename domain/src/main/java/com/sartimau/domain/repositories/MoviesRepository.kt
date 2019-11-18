package com.sartimau.domain.repositories

import com.sartimau.domain.utils.Result
import com.sartimau.domain.entities.Movie

interface MoviesRepository {
    fun getMoviesByCategory(category: String): Result<List<Movie>>
}