package com.sartimau.data.service.response

import com.google.gson.annotations.SerializedName
import com.sartimau.domain.utils.Constants.MINUS_ONE

data class MovieServiceResponse(
    @SerializedName("page")
    var page: Int = -1,
    @SerializedName("results")
    var results: List<MovieItemService> = ArrayList(),
    @SerializedName("total_pages")
    var totalPages: Int = MINUS_ONE,
    @SerializedName("total_results")
    var totalResults: Int = MINUS_ONE
)
