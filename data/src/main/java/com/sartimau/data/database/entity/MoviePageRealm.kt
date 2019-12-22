package com.sartimau.data.database.entity

import com.sartimau.domain.utils.Constants.EMPTY_STRING
import com.sartimau.domain.utils.Constants.MINUS_ONE
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MoviePageRealm(
    @PrimaryKey
    var id: String = EMPTY_STRING,
    var page: Int = -1,
    var results: RealmList<MovieItemRealm>? = null,
    var totalPages: Int = MINUS_ONE,
    var totalResults: Int = MINUS_ONE,
    var category: String = EMPTY_STRING
) : RealmObject()
