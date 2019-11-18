package com.sartimau.moviesdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sartimau.domain.entities.Movie

import com.sartimau.moviesdb.R
import com.sartimau.moviesdb.utils.LiveDataEvent
import com.sartimau.moviesdb.viewmodels.MoviesData
import com.sartimau.moviesdb.viewmodels.MoviesStatus
import com.sartimau.moviesdb.viewmodels.MoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularTabFragment : Fragment() {

    private val viewModel by viewModel<MoviesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_popular_tab, container, false)
        viewModel.mainState.observe(::getLifecycle, ::updateUI)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPopularMovies()
    }

    private fun updateUI(moviesData: LiveDataEvent<MoviesData<Movie>>) {
        // in this case, we need to use peekContent because we use this several times to update the UI
        // in case that we will only use the characterData one time we have to use getContentIfNotHandled
        when (moviesData.peekContent().moviesStatus) {
            MoviesStatus.SUCCESSFUL -> {
                Toast.makeText(activity, "SUCCESSFUL", Toast.LENGTH_SHORT).show()
            }
            MoviesStatus.ERROR -> {
                Toast.makeText(activity, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val TITLE = "Popular"
    }
}
