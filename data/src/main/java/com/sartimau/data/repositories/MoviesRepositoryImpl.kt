package com.sartimau.data.repositories

import com.sartimau.data.database.MovieDatabase
import com.sartimau.data.service.MovieService
import com.sartimau.domain.entities.Movie
import com.sartimau.domain.repositories.MoviesRepository
import com.sartimau.domain.utils.Result

class MoviesRepositoryImpl(
    private val movieService: MovieService,
    private val movieDatabase: MovieDatabase
) : MoviesRepository {

    override fun getPopularMovies(page: Int): Result<Movie> {
        val movieResult = movieService.getPopularMovies(page)
//        return if (movieResult is Result.Success) {
//            insertOrUpdateCharacter(movieResult.data)
           return movieResult
//        } else {
//            movieDatabase.getMoviesByCategory("POPULAR")
//        }
    }

    override fun getTopRatedMovies(page: Int): Result<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUpcomingMovies(page: Int): Result<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun insertOrUpdateCharacter(movies: Movie) {
        movieDatabase.insertOrUpdateCharacter(movies)
    }
}
