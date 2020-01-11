package com.sartimau.moviesdb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sartimau.domain.entities.MovieItem
import com.sartimau.moviesdb.R
import com.sartimau.moviesdb.utils.BASE_MOVIE_URL
import com.sartimau.moviesdb.utils.getImageByUrl
import kotlinx.android.synthetic.main.item_movie.view.movieAverageVotes
import kotlinx.android.synthetic.main.item_movie.view.movieImage
import kotlinx.android.synthetic.main.item_movie.view.movieOverview
import kotlinx.android.synthetic.main.item_movie.view.movieTitle

private const val MOVIE = 0
private const val LOADING = 1

class MoviesAdapter(private val movies: MutableList<MovieItem>, private val listener: MovieListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoadingAdded = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val movieItemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val loadingItemView = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)

        return when (viewType) {
            MOVIE -> MoviesAdapterViewHolder(movieItemView, listener)
            LOADING -> LoadingViewHolder(loadingItemView)
            else -> throw IllegalArgumentException("Wrong view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            MOVIE -> (holder as MoviesAdapterViewHolder).bind(movies[position])
            LOADING -> { } // do nothing
        }
    }

    override fun getItemCount(): Int = movies.size

    override fun getItemViewType(position: Int): Int {
        return if (position == movies.size - 1 && isLoadingAdded) LOADING else MOVIE
    }

    fun addMovies(newMovies: List<MovieItem>) {
        newMovies.forEach {
            movies.add(it)
            notifyItemInserted(movies.size - 1)
        }
    }

    fun addLoadingFooter() {
        isLoadingAdded = true
        movies.add(MovieItem())
        notifyItemInserted(movies.size - 1)
    }

    fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = movies.size - 1
        movies.removeAt(position)
        notifyItemRemoved(position)
    }
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

class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

typealias MovieListener = (MovieItem) -> Unit

abstract class PaginationScrollListener
    (private var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    abstract val totalPageCount: Int

    abstract val isLastPage: Boolean

    abstract val isLoading: Boolean

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                firstVisibleItemPosition >= 0 &&
                totalItemCount >= totalPageCount
            ) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()
}