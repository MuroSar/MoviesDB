package com.sartimau.moviesdb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sartimau.domain.entities.MovieItem
import com.sartimau.moviesdb.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.movieAverageVotes
import kotlinx.android.synthetic.main.item_movie.view.movieImage
import kotlinx.android.synthetic.main.item_movie.view.movieOverview
import kotlinx.android.synthetic.main.item_movie.view.movieTitle

private const val BASE_MOVIE_URL = "https://image.tmdb.org/t/p/w500/"

class MoviesAdapter(private val movies: List<MovieItem> = emptyList(), private val listener: MovieListener) :
    RecyclerView.Adapter<MoviesAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MoviesAdapterViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: MoviesAdapterViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}

class MoviesAdapterViewHolder(view: View, val listener: MovieListener) : RecyclerView.ViewHolder(view) {

    fun bind(movie: MovieItem) = with(itemView) {
        movieImage.getImageByUrl("$BASE_MOVIE_URL${movie.posterPath}")

        movieTitle.text = movie.title
        movieOverview.text = movie.overview
        movieAverageVotes.text = movie.voteAverage.toString()

        setOnClickListener { listener(movie) }
    }
}

typealias MovieListener = (MovieItem) -> Unit

fun ImageView.getImageByUrl(url: String) {
    Picasso.with(context)
        .load(url)
        .placeholder(R.drawable.movie_placeholder)
        .fit()
        .centerCrop()
        .into(this)
}