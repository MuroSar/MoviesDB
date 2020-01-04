package com.sartimau.data.repositories

import com.sartimau.data.database.MovieDatabase
import com.sartimau.data.service.MovieService
import com.sartimau.domain.entities.MoviePage
import com.sartimau.domain.repositories.MoviesRepository
import com.sartimau.domain.utils.Constants.CATEGORY_POPULAR
import com.sartimau.domain.utils.Result

class MoviesRepositoryImpl(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase
) : MoviesRepository {

    override fun getPopularMovies(page: Int, networkAvailable: Boolean): Result<MoviePage> {
        return if (networkAvailable) {
            val movieResult = movieService.getPopularMovies(page)
            if (movieResult is Result.Success) {
                insertOrUpdateCharacter(movieResult.data)
                movieResult
            } else {
                movieDatabase.getMoviesByPageAndCategory(page, CATEGORY_POPULAR)
            }
        } else {
            movieDatabase.getMoviesByPageAndCategory(page, CATEGORY_POPULAR)
        }
    }

    override fun getTopRatedMovies(page: Int, networkAvailable: Boolean): Result<MoviePage> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUpcomingMovies(page: Int, networkAvailable: Boolean): Result<MoviePage> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun insertOrUpdateCharacter(moviePage: MoviePage) {
        movieDatabase.insertOrUpdateCharacter(moviePage)
    }
}
