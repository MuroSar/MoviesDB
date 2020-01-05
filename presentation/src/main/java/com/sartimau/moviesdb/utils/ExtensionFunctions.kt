package com.sartimau.moviesdb.utils

import android.widget.ImageView
import com.sartimau.moviesdb.R
import com.squareup.picasso.Picasso

fun ImageView.getImageByUrl(url: String) {
    Picasso.with(context)
        .load(url)
        .placeholder(R.drawable.movie_placeholder)
        .fit()
        .centerCrop()
        .into(this)
}