package com.sartimau.data.service

import com.sartimau.data.service.api.MovieApi
import com.sartimau.domain.utils.Result
import com.sartimau.data.MoviesRequestGenerator
import com.sartimau.data.MoviesRequestGenerator.Companion.API_KEY_V3
import com.sartimau.data.mapper.MovieMapperService
import com.sartimau.domain.entities.Movie

class MovieService {

    private val api: MoviesRequestGenerator = MoviesRequestGenerator()
    private val mapper: MovieMapperService = MovieMapperService()

    fun getPopularMovies(page: Int): Result<List<Movie>> {
        val callResponse = api.createService(MovieApi::class.java).getPopularMovies(page, API_KEY_V3)
        val response = callResponse.execute()
        if (response != null) {
            if (response.isSuccessful) {
//                response.body()?.data?.let { mapper.transform(it) }?.let { return Result.Success(it) }
            }
            return Result.Failure(Exception(response.message()))
        }
        return Result.Failure(Exception("Bad request/response"))
    }

}
