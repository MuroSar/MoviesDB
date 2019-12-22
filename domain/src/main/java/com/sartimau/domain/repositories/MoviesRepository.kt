package com.sartimau.domain.repositories

import com.sartimau.domain.utils.Result
import com.sartimau.domain.entities.MoviePage

interface MoviesRepository {
    fun getPopularMovies(page: Int, networkAvailable: Boolean): Result<MoviePage>
    fun getTopRatedMovies(page: Int, networkAvailable: Boolean): Result<MoviePage>
    fun getUpcomingMovies(page: Int, networkAvailable: Boolean): Result<MoviePage>
}