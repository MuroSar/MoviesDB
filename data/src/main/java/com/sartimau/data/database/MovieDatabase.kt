package com.sartimau.data.database

import com.sartimau.data.database.entity.MoviePageRealm
import com.sartimau.data.mapper.MovieMapperLocal
import com.sartimau.domain.entities.MoviePage
import com.sartimau.domain.utils.Result
import io.realm.Realm

const val MOVIE_PAGE_NOT_FOUND_ERROR = "Movie Page not found"

class MovieDatabase {

    private val mapper: MovieMapperLocal = MovieMapperLocal()

    fun getMoviesByPageAndCategory(page: Int, category: String): Result<MoviePage> {
        Realm.getDefaultInstance().use {
            val movies = it.where(MoviePageRealm::class.java)
                .equalTo("id", "$category-$page")
                .findFirst()

            movies?.let { return Result.Success(mapper.transform(movies)) }
            return Result.Failure(Exception(MOVIE_PAGE_NOT_FOUND_ERROR))
        }
    }

    fun insertOrUpdateCharacter(moviePage: MoviePage) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(mapper.transformToRepository(moviePage))
            }
        }
    }
}
