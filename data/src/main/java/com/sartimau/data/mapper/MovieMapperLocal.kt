package com.sartimau.data.mapper

import com.sartimau.data.database.entity.MovieItemRealm
import com.sartimau.data.database.entity.MovieRealm
import com.sartimau.domain.entities.Movie
import com.sartimau.domain.entities.MovieItem
import io.realm.RealmList

class MovieMapperLocal : BaseMapperRepository<MovieRealm, Movie> {

    override fun transform(entitie: MovieRealm): Movie {
        return Movie(
            entitie.created_by,
            entitie.description,
            entitie.favorite_count,
            entitie.id,
            entitie.iso_639_1,
            entitie.item_count,
            transformMovieItem(entitie.items),
            entitie.name,
            entitie.poster_path
        )
    }

    private fun transformMovieItem(items: RealmList<MovieItemRealm>?): List<MovieItem> {
        val movies = ArrayList<MovieItem>()
        items?.forEach {
            movies.add(
                MovieItem(
                    it.adult,
                    it.backdrop_path,
                    transformGenreIds(it.genre_ids),
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

    private fun transformGenreIds(ids: RealmList<Int>?): List<Int> {
        val result = ArrayList<Int>()
        ids?.forEach {
            result.add(it)
        }
        return result
    }

    override fun transformToRepository(movie: Movie): MovieRealm {
        return MovieRealm(
            movie.id,
            movie.created_by,
            movie.description,
            movie.favorite_count,
            movie.iso_639_1,
            movie.item_count,
            transformMovieItemToRepository(movie.items),
            movie.name,
            movie.poster_path
        )
    }

    private fun transformMovieItemToRepository(items: List<MovieItem>): RealmList<MovieItemRealm> {
        val movies = RealmList<MovieItemRealm>()
        items.forEach {
            movies.add(
                MovieItemRealm(
                    it.id,
                    it.adult,
                    it.backdrop_path,
                    transformGenreIdsToRepository(it.genre_ids),
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

    private fun transformGenreIdsToRepository(ids: List<Int>?): RealmList<Int> {
        val result = RealmList<Int>()
        ids?.forEach {
            result.add(it)
        }
        return result
    }
}
