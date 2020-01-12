package com.sartimau.moviesdb.utils

import android.app.AlertDialog
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sartimau.domain.entities.MovieItem
import com.sartimau.moviesdb.R

fun showMovieDialog(fragment: Fragment, movie: MovieItem) {
    val builder = AlertDialog.Builder(fragment.context)
    val view = fragment.layoutInflater.inflate(R.layout.dialog_movie, null)

    val title = view.findViewById<TextView>(R.id.title)
    val adult = view.findViewById<TextView>(R.id.adult_info)
    val genreId = view.findViewById<TextView>(R.id.genre_id_info)
    val id = view.findViewById<TextView>(R.id.id_info)
    val originalLanguage = view.findViewById<TextView>(R.id.original_language_info)
    val originalTitle = view.findViewById<TextView>(R.id.original_title_info)
    val overview = view.findViewById<TextView>(R.id.overview_info)
    val popularity = view.findViewById<TextView>(R.id.popularity_info)
    val releaseDate = view.findViewById<TextView>(R.id.release_date_info)
    val video = view.findViewById<TextView>(R.id.video_info)
    val voteAverage = view.findViewById<TextView>(R.id.vote_average_info)
    val voteCount = view.findViewById<TextView>(R.id.vote_count_info)
    val posterPath = view.findViewById<TextView>(R.id.poster_path_info)
    val imagePoster = view.findViewById<ImageView>(R.id.image_poster)
    val backdropPath = view.findViewById<TextView>(R.id.backdrop_path_info)
    val imageBackdrop = view.findViewById<ImageView>(R.id.image_backdrop)

    title.text = movie.title
    adult.text = movie.adult.toString()
    genreId.text = movie.genreIds.toString()
    id.text = movie.id.toString()
    originalLanguage.text = movie.originalLanguage
    originalTitle.text = movie.originalTitle
    overview.text = movie.overview
    popularity.text = movie.popularity.toString()
    releaseDate.text = movie.releaseDate
    video.text = movie.video.toString()
    voteAverage.text = movie.voteAverage.toString()
    voteCount.text = movie.voteCount.toString()
    posterPath.text = movie.posterPath
    if (movie.posterPath.isNotEmpty()) {
        imagePoster.getImageByUrl("$BASE_MOVIE_URL${movie.posterPath}")
    }
    backdropPath.text = movie.backdropPath
    if (movie.backdropPath.isNotEmpty()) {
        imageBackdrop.getImageByUrl("$BASE_MOVIE_URL${movie.backdropPath}")
    }

    builder.setView(view)
    val dialog = builder.create()
    dialog.setCancelable(true)
    dialog.show()
}

fun showNotificationDialog(fragment: Fragment, title: String, content: String) {
    val builder = AlertDialog.Builder(fragment.context)
    val view = fragment.layoutInflater.inflate(R.layout.dialog_notification, null)

    val notificationTitle = view.findViewById<TextView>(R.id.notification_title)
    val notificationContent = view.findViewById<TextView>(R.id.notification_content)
    val notificationButton = view.findViewById<Button>(R.id.notification_button)

    notificationTitle.text = title
    notificationContent.text = content

    builder.setView(view)
    val dialog = builder.create()
    dialog.setCancelable(true)
    dialog.show()

    notificationButton.setOnClickListener { dialog.dismiss() }
}