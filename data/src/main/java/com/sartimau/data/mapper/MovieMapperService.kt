package com.sartimau.data.mapper

import com.sartimau.data.service.response.MovieItemService
import com.sartimau.data.service.response.MovieServiceResponse
import com.sartimau.domain.entities.MovieItem
import com.sartimau.domain.entities.MoviePage
import com.sartimau.domain.utils.Constants.EMPTY_STRING

open class MovieMapperService : BaseMapperRepository<MovieServiceResponse, MoviePage> {

    override fun transform(response: MovieServiceResponse): MoviePage {
        return MoviePage(
            EMPTY_STRING,
            response.page,
            transformMovieItem(response.results),
            response.totalPages,
            response.totalResults,
            EMPTY_STRING
        )
    }

    private fun transformMovieItem(items: List<MovieItemService>): List<MovieItem> {
        val movies = ArrayList<MovieItem>()
        items.forEach {
            movies.add(
                MovieItem(
                    it.adult,
                    it.backdropPath ?: EMPTY_STRING,
                    it.genreIds,
                    it.id,
                    it.originalLanguage,
                    it.originalTitle,
                    it.overview,
                    it.popularity,
                    it.posterPath ?: EMPTY_STRING,
                    it.releaseDate,
                    it.title,
                    it.video,
                    it.voteAverage,
                    it.voteCount
                )
            )
        }
        return movies
    }

    override fun transformToRepository(movie: MoviePage): MovieServiceResponse {
        return MovieServiceResponse(
            movie.page,
            transformMovieItemToRepository(movie.results),
            movie.totalPages,
            movie.totalResults
        )
    }

    private fun transformMovieItemToRepository(items: List<MovieItem>): List<MovieItemService> {
        val movies = ArrayList<MovieItemService>()
        items.forEach {
            movies.add(
                MovieItemService(
                    it.adult,
                    it.backdropPath,
                    it.genreIds,
                    it.id,
                    it.originalLanguage,
                    it.originalTitle,
                    it.overview,
                    it.popularity,
                    it.posterPath,
                    it.releaseDate,
                    it.title,
                    it.video,
                    it.voteAverage,
                    it.voteCount
                )
            )
        }
        return movies
    }
}
