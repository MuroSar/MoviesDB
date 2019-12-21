package com.sartimau.data.database.entity

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MovieRealm(
    @PrimaryKey
    var id: String = "",
    var created_by: String = "",
    var description: String = "",
    var favorite_count: Int = 0,
    var iso_639_1: String = "",
    var item_count: Int = 0,
    var items: RealmList<MovieItemRealm>? = null,
    var name: String = "",
    var poster_path: String = ""
) : RealmObject()
