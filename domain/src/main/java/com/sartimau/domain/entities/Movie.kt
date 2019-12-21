package com.sartimau.domain.entities

data class Movie(
    val created_by: String,
    val description: String,
    val favorite_count: Int,
    val id: String,
    val iso_639_1: String,
    val item_count: Int,
    val items: List<MovieItem>,
    val name: String,
    val poster_path: String
)

class MovieItem(
    var adult: Boolean = false,
    var backdrop_path: String = "",
    var genre_ids: List<Int>? = null,
    var id: Int = 0,
    var media_type: String = "",
    var original_language: String = "",
    var original_title: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var poster_path: String = "",
    var release_date: String = "",
    var title: String = "",
    var video: Boolean = false,
    var vote_average: Double = 0.0,
    var vote_count: Int = 0,
    var category: String = "general"
)