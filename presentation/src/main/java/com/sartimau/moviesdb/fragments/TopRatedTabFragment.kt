package com.sartimau.moviesdb.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sartimau.domain.entities.MoviePage
import com.sartimau.domain.utils.Constants.TOTAL_TOP_RATED_MOVIES_BY_PAGE
import com.sartimau.domain.utils.Constants.TOTAL_TOP_RATED_PAGES

import com.sartimau.moviesdb.R
import com.sartimau.moviesdb.adapters.MoviesAdapter
import com.sartimau.moviesdb.adapters.PaginationScrollListener
import com.sartimau.moviesdb.utils.LiveDataEvent
import com.sartimau.moviesdb.utils.NetworkUtils
import com.sartimau.moviesdb.utils.NetworkUtils.isNetworkAvailable
import com.sartimau.moviesdb.utils.showMovieDialog
import com.sartimau.moviesdb.utils.showNotificationDialog
import com.sartimau.moviesdb.viewmodels.MoviesData
import com.sartimau.moviesdb.viewmodels.MoviesStatus
import com.sartimau.moviesdb.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_top_rated_tab.recycler
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopRatedTabFragment : Fragment() {

    private val viewModel by viewModel<MoviesViewModel>()
    private var isLastPageLoaded: Boolean = false
    private var isLoadingNextPage: Boolean = false
    private var page: Int = 1

    private lateinit var adapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_top_rated_tab, container, false)
        viewModel.mainState.observe(::getLifecycle, ::updateUI)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopRatedMovies(NetworkUtils.isNetworkAvailable(this.context), page)
    }

    private fun updateUI(moviesData: LiveDataEvent<MoviesData<MoviePage>>) {
        // in this case, we need to use peekContent because we use this several times to update the UI
        // in case that we will only use the characterData one time we have to use getContentIfNotHandled
        when (moviesData.peekContent().moviesStatus) {
            MoviesStatus.SUCCESSFUL_FIRST_PAGE -> {
                moviesData.peekContent().data?.results?.let {
                    adapter = MoviesAdapter(it.toMutableList()) { character -> showMovieDialog(this, character) }
                    recycler.adapter = adapter
                    recycler.layoutManager = LinearLayoutManager(this.context)
                    recycler.addOnScrollListener(getPaginationScrollListener(this.context, recycler.layoutManager as LinearLayoutManager))
                }
            }
            MoviesStatus.ERROR_FIRST_PAGE -> {
                moviesData.peekContent().error?.message?.let {
                    showNotificationDialog(this, getString(R.string.error), it)
                }
            }
            MoviesStatus.SUCCESSFUL_NEXT_PAGE -> {
                moviesData.peekContent().data?.results?.let {
                    adapter.removeLoadingFooter()
                    isLoadingNextPage = false
                    adapter.addMovies(it)
                    isLastPageLoaded = page == TOTAL_TOP_RATED_PAGES
                }
            }
            MoviesStatus.ERROR_NEXT_PAGE -> {
                moviesData.peekContent().error?.message?.let {
                    adapter.removeLoadingFooter()
                    isLoadingNextPage = false
                    page--
                    showNotificationDialog(this, getString(R.string.error), getString(R.string.error_next_page))
                }
            }
        }
    }

    private fun getPaginationScrollListener(context: Context?, linearLayoutManager: LinearLayoutManager): PaginationScrollListener {
        return object : PaginationScrollListener(linearLayoutManager) {

            override val totalPageCount: Int
                get() = TOTAL_TOP_RATED_MOVIES_BY_PAGE

            override val isLastPage: Boolean
                get() = isLastPageLoaded

            override val isLoading: Boolean
                get() = isLoadingNextPage

            override fun loadMoreItems() {
                adapter.addLoadingFooter()
                isLoadingNextPage = true
                page++

                viewModel.getTopRatedMoviesNextPage(isNetworkAvailable(context), page)
            }
        }
    }

    companion object {
        const val TITLE = "Top Rated"
    }
}
