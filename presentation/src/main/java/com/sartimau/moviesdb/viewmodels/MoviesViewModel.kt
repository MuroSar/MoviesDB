package com.sartimau.moviesdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sartimau.domain.entities.Movie
import com.sartimau.domain.usecases.GetMovieByCategoryUseCase
import com.sartimau.moviesdb.utils.LiveDataEvent

class MoviesViewModel(val getMovieByCategoryUseCase: GetMovieByCategoryUseCase) : ViewModel() {

    private var mutableMainState: MutableLiveData<LiveDataEvent<MoviesData<Movie>>> = MutableLiveData()
    val mainState: LiveData<LiveDataEvent<MoviesData<Movie>>>
        get() {
            return mutableMainState
        }
}

data class MoviesData<String>(var moviesStatus: MoviesStatus, var movies: List<Movie>? = null)

enum class MoviesStatus {
    SUCCESSFUL,
    ERROR,
    LOADING
}