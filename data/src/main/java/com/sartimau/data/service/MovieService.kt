package com.sartimau.data.service

import com.sartimau.data.service.api.MovieApi
import com.sartimau.domain.utils.Result
import com.sartimau.data.MoviesRequestGenerator
import com.sartimau.data.MoviesRequestGenerator.Companion.API_KEY_V3
import com.sartimau.data.mapper.MovieMapperService
import com.sartimau.domain.entities.MoviePage
import com.sartimau.domain.utils.Constants.CATEGORY_POPULAR
import com.sartimau.domain.utils.Constants.CATEGORY_TOP_RATED

class MovieService {

    private val api: MoviesRequestGenerator = MoviesRequestGenerator()
    private val mapper: MovieMapperService = MovieMapperService()

    fun getPopularMovies(page: Int): Result<MoviePage> {
        val callResponse = api.createService(MovieApi::class.java).getPopularMovies(page, API_KEY_V3)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                response.body()?.let { mapper.transform(it) }?.let {
                    it.category = CATEGORY_POPULAR
                    it.id = "${it.category}-${it.page}"
                    return Result.Success(it)
                }
            }
            return Result.Failure(Exception(response.message()))
        }
        return Result.Failure(Exception("Bad request/response"))
    }

    fun getTopRatedMovies(page: Int): Result<MoviePage> {
        val callResponse = api.createService(MovieApi::class.java).getTopRatedMovies(page, API_KEY_V3)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
                response.body()?.let { mapper.transform(it) }?.let {
                    it.category = CATEGORY_TOP_RATED
                    it.id = "${it.category}-${it.page}"
                    return Result.Success(it)
                }
            }
            return Result.Failure(Exception(response.message()))
        }
        return Result.Failure(Exception("Bad request/response"))
    }

}
