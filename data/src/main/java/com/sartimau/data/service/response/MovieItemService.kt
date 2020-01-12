package com.sartimau.data.service.response

import com.google.gson.annotations.SerializedName
import com.sartimau.domain.utils.Constants.EMPTY_STRING
import com.sartimau.domain.utils.Constants.ZERO_FLOAT
import com.sartimau.domain.utils.Constants.ZERO_INT

class MovieItemService(
    @SerializedName("adult")
    var adult: Boolean = false,
    @SerializedName("backdrop_path")
    var backdropPath: String? = EMPTY_STRING,
    @SerializedName("genre_ids")
    var genreIds: List<Int>? = null,
    @SerializedName("id")
    var id: Int = ZERO_INT,
    @SerializedName("original_language")
    var originalLanguage: String = EMPTY_STRING,
    @SerializedName("original_title")
    var originalTitle: String = EMPTY_STRING,
    @SerializedName("overview")
    var overview: String = EMPTY_STRING,
    @SerializedName("popularity")
    var popularity: Double = ZERO_FLOAT,
    @SerializedName("poster_path")
    var posterPath: String? = EMPTY_STRING,
    @SerializedName("release_date")
    var releaseDate: String = EMPTY_STRING,
    @SerializedName("title")
    var title: String = EMPTY_STRING,
    @SerializedName("video")
    var video: Boolean = false,
    @SerializedName("vote_average")
    var voteAverage: Double = ZERO_FLOAT,
    @SerializedName("vote_count")
    var voteCount: Int = ZERO_INT
)