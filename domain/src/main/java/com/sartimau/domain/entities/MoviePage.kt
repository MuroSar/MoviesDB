package com.sartimau.domain.entities

import com.sartimau.domain.utils.Constants.EMPTY_STRING
import com.sartimau.domain.utils.Constants.MINUS_ONE

data class MoviePage(
    var id: String= EMPTY_STRING,
    var page: Int = -1,
    var results: List<MovieItem> = ArrayList(),
    var totalPages: Int = MINUS_ONE,
    var totalResults: Int = MINUS_ONE,
    var category: String
)