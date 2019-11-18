package com.sartimau.data.database.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MovieRealm(
    @PrimaryKey
    var id: Int = 0,
    var name: String = ""
) : RealmObject()
