package com.sartimau.domain.repositories

import com.sartimau.domain.utils.Result
import com.sartimau.domain.entities.Movie

interface MoviesRepository {
    fun getPopularMovies(page: Int): Result<Movie>
    fun getTopRatedMovies(page: Int): Result<Movie>
    fun getUpcomingMovies(page: Int): Result<Movie>
}