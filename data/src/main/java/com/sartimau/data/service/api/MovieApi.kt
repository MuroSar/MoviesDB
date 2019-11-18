package com.sartimau.data.service.api

import com.sartimau.data.database.response.DataBaseResponse
import com.sartimau.data.service.response.MovieBaseResponse
import com.sartimau.data.service.response.MovieServiceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("list/{list_id}")
    fun getMovies(
        @Path("list_id") listID: Int,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieBaseResponse<DataBaseResponse<MovieServiceResponse>>>

    @GET("movie/popular/")
    fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieBaseResponse<DataBaseResponse<MovieServiceResponse>>>

    @GET("movie/top_rated/")
    fun getTopRatedMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieBaseResponse<DataBaseResponse<MovieServiceResponse>>>

    @GET("movie/upcoming/")
    fun getUpcomingMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieBaseResponse<DataBaseResponse<MovieServiceResponse>>>
}
