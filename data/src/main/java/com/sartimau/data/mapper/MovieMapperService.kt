package com.sartimau.data.mapper

import com.sartimau.data.service.response.MovieServiceResponse
import com.sartimau.domain.entities.Movie

open class MovieMapperService : BaseMapperRepository<List<MovieServiceResponse>, List<Movie>> {

    override fun transform(entities: List<MovieServiceResponse>): List<Movie> {
        val movies = ArrayList<Movie>()
        entities.forEach { movies.add(Movie(it.id, it.name)) }

        return movies
    }

    override fun transformToRepository(movies: List<Movie>): List<MovieServiceResponse> {
        val entities = ArrayList<MovieServiceResponse>()
        movies.forEach { entities.add(MovieServiceResponse(it.id, it.name)) }

        return entities
    }
}
