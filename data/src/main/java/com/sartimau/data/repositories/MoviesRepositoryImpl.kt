package com.sartimau.data.repositories

import com.sartimau.data.database.MovieDatabase
import com.sartimau.data.service.MovieService
import com.sartimau.domain.entities.MoviePage
import com.sartimau.domain.repositories.MoviesRepository
import com.sartimau.domain.utils.Constants.CATEGORY_POPULAR
import com.sartimau.domain.utils.Constants.CATEGORY_TOP_RATED
import com.sartimau.domain.utils.Constants.CATEGORY_UPCOMING
import com.sartimau.domain.utils.Result

class MoviesRepositoryImpl(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase
) : MoviesRepository {

    override fun getPopularMovies(page: Int, networkAvailable: Boolean): Result<MoviePage> {
        return if (networkAvailable) {
            val movieResult = movieService.getPopularMovies(page)
            if (movieResult is Result.Success) {
                insertOrUpdateMovie(movieResult.data)
                movieResult
            } else {
                movieDatabase.getMoviesByPageAndCategory(page, CATEGORY_POPULAR)
            }
        } else {
            movieDatabase.getMoviesByPageAndCategory(page, CATEGORY_POPULAR)
        }
    }

    override fun getTopRatedMovies(page: Int, networkAvailable: Boolean): Result<MoviePage> {
        return if (networkAvailable) {
            val movieResult = movieService.getTopRatedMovies(page)
            if (movieResult is Result.Success) {
                insertOrUpdateMovie(movieResult.data)
                movieResult
            } else {
                movieDatabase.getMoviesByPageAndCategory(page, CATEGORY_TOP_RATED)
            }
        } else {
            movieDatabase.getMoviesByPageAndCategory(page, CATEGORY_TOP_RATED)
        }
    }

    override fun getUpcomingMovies(page: Int, networkAvailable: Boolean): Result<MoviePage> {
        return if (networkAvailable) {
            val movieResult = movieService.getUpcomingMovies(page)
            if (movieResult is Result.Success) {
                insertOrUpdateMovie(movieResult.data)
                movieResult
            } else {
                movieDatabase.getMoviesByPageAndCategory(page, CATEGORY_UPCOMING)
            }
        } else {
            movieDatabase.getMoviesByPageAndCategory(page, CATEGORY_UPCOMING)
        }
    }

    private fun insertOrUpdateMovie(moviePage: MoviePage) {
        movieDatabase.insertOrUpdateCharacter(moviePage)
    }
}
