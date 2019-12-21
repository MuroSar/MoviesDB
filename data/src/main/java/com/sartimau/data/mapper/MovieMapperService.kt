package com.sartimau.data.mapper

import com.sartimau.data.service.response.Item
import com.sartimau.data.service.response.MovieServiceResponse
import com.sartimau.domain.entities.Movie
import com.sartimau.domain.entities.MovieItem

open class MovieMapperService : BaseMapperRepository<MovieServiceResponse, Movie> {

    override fun transform(response: MovieServiceResponse): Movie {
        return Movie(
            response.created_by,
            response.description,
            response.favorite_count,
            response.id,
            response.iso_639_1,
            response.item_count,
            transformMovieItem(response.items),
            response.name,
            response.poster_path
        )
    }

    private fun transformMovieItem(items: List<Item>): List<MovieItem> {
        val movies = ArrayList<MovieItem>()
        items.forEach {
            movies.add(
                MovieItem(
                    it.adult,
                    it.backdrop_path,
                    it.genre_ids,
                    it.id,
                    it.media_type,
                    it.original_language,
                    it.original_title,
                    it.overview,
                    it.popularity,
                    it.poster_path,
                    it.release_date,
                    it.title,
                    it.video,
                    it.vote_average,
                    it.vote_count,
                    it.category
                )
            )
        }
        return movies
    }

    override fun transformToRepository(movie: Movie): MovieServiceResponse {
        return MovieServiceResponse(
            movie.created_by,
            movie.description,
            movie.favorite_count,
            movie.id,
            movie.iso_639_1,
            movie.item_count,
            transformMovieItemToRepository(movie.items),
            movie.name,
            movie.poster_path
        )
    }

    private fun transformMovieItemToRepository(items: List<MovieItem>): List<Item> {
        val movies = ArrayList<Item>()
        items.forEach {
            movies.add(
                Item(
                    it.adult,
                    it.backdrop_path,
                    it.genre_ids,
                    it.id,
                    it.media_type,
                    it.original_language,
                    it.original_title,
                    it.overview,
                    it.popularity,
                    it.poster_path,
                    it.release_date,
                    it.title,
                    it.video,
                    it.vote_average,
                    it.vote_count,
                    it.category
                )
            )
        }
        return movies
    }
}
