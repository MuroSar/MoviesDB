package com.sartimau.data.mapper

import com.sartimau.data.database.entity.MovieItemRealm
import com.sartimau.data.database.entity.MoviePageRealm
import com.sartimau.domain.entities.MovieItem
import com.sartimau.domain.entities.MoviePage
import io.realm.RealmList

class MovieMapperLocal : BaseMapperRepository<MoviePageRealm, MoviePage> {

    override fun transform(entitie: MoviePageRealm): MoviePage {
        return MoviePage(
            entitie.id,
            entitie.page,
            transformMovieItem(entitie.results),
            entitie.totalPages,
            entitie.totalResults,
            entitie.category
        )
    }

    private fun transformMovieItem(items: RealmList<MovieItemRealm>?): List<MovieItem> {
        val movies = ArrayList<MovieItem>()
        items?.forEach {
            movies.add(
                MovieItem(
                    it.adult,
                    it.backdropPath,
                    transformGenreIds(it.genreIds),
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

    private fun transformGenreIds(ids: RealmList<Int>?): List<Int> {
        val result = ArrayList<Int>()
        ids?.forEach {
            result.add(it)
        }
        return result
    }

    override fun transformToRepository(moviePage: MoviePage): MoviePageRealm {
        return MoviePageRealm(
            moviePage.id,
            moviePage.page,
            transformMovieItemToRepository(moviePage.results),
            moviePage.totalPages,
            moviePage.totalResults,
            moviePage.category
        )
    }

    private fun transformMovieItemToRepository(items: List<MovieItem>): RealmList<MovieItemRealm> {
        val movies = RealmList<MovieItemRealm>()
        items.forEach {
            movies.add(
                MovieItemRealm(
                    it.id,
                    it.adult,
                    it.backdropPath,
                    transformGenreIdsToRepository(it.genreIds),
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

    private fun transformGenreIdsToRepository(ids: List<Int>?): RealmList<Int> {
        val result = RealmList<Int>()
        ids?.forEach {
            result.add(it)
        }
        return result
    }
}
