package com.sartimau.data.database.response

import com.google.gson.annotations.SerializedName

class DataBaseResponse<T>(
        @SerializedName("results") val movies: List<T>,
        val offset: Int,
        val limit: Int,
        val total: Int
)
