package com.sartimau.data.database.response

import com.google.gson.annotations.SerializedName
import com.sartimau.data.service.response.MovieBaseResponse

class DataBaseResponse<T>(
        @SerializedName("results") val movies: List<MovieBaseResponse<T>>,
        val offset: Int,
        val limit: Int,
        val total: Int
)
