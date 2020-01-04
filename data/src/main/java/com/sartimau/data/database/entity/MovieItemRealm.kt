package com.sartimau.data.database.entity

import com.sartimau.domain.utils.Constants.EMPTY_STRING
import com.sartimau.domain.utils.Constants.ZERO_FLOAT
import com.sartimau.domain.utils.Constants.ZERO_INT
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MovieItemRealm(
    @PrimaryKey
    var id: Int = ZERO_INT,
    var adult: Boolean = false,
    var backdropPath: String = EMPTY_STRING,
    var genreIds: RealmList<Int>? = null,
    var originalLanguage: String = EMPTY_STRING,
    var originalTitle: String = EMPTY_STRING,
    var overview: String = EMPTY_STRING,
    var popularity: Double = ZERO_FLOAT,
    var posterPath: String = EMPTY_STRING,
    var releaseDate: String = EMPTY_STRING,
    var title: String = EMPTY_STRING,
    var video: Boolean = false,
    var voteAverage: Double = ZERO_FLOAT,
    var voteCount: Int = ZERO_INT
) : RealmObject()