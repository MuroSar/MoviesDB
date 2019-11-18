package com.sartimau.data.mapper

import com.sartimau.data.database.entity.MovieRealm
import com.sartimau.domain.entities.Movie

class MovieMapperLocal : BaseMapperRepository<List<MovieRealm>, List<Movie>> {

    override fun transform(entities: List<MovieRealm>): List<Movie> {
        val movies = ArrayList<Movie>()
        entities.forEach { movies.add(Movie(it.id, it.name)) }

        return movies
    }

    override fun transformToRepository(movies: List<Movie>): List<MovieRealm> {
        val entities = ArrayList<MovieRealm>()
        movies.forEach { entities.add(MovieRealm(it.id, it.name)) }

        return entities
    }
}
