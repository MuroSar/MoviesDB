package com.sartimau.data.database

import com.sartimau.data.database.entity.MovieRealm
import com.sartimau.data.mapper.MovieMapperLocal
import com.sartimau.domain.entities.Movie
import com.sartimau.domain.utils.Result
import io.realm.Realm

class MovieDatabase {

    fun getMoviesByCategory(category: String): Result<Movie> {
        val mapper = MovieMapperLocal()
        Realm.getDefaultInstance().use {
            val movies= it.where(MovieRealm::class.java).findFirst()//.equalTo("Category", category).findAll()
            movies?.let { return Result.Success(mapper.transform(movies)) }
            return Result.Failure(Exception("Character not found"))
        }
    }

    fun insertOrUpdateCharacter(movies: Movie) {
        val mapper = MovieMapperLocal()
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(mapper.transformToRepository(movies))
            }
        }
    }
}
