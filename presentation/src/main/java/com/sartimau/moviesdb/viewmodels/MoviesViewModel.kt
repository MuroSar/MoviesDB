package com.sartimau.moviesdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sartimau.domain.entities.Movie
import com.sartimau.domain.usecases.MovieUseCase
import com.sartimau.domain.utils.Constants.CATEGORY_POPULAR
import com.sartimau.moviesdb.utils.LiveDataEvent
import com.sartimau.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MoviesViewModel(val movieUseCase: MovieUseCase) : ViewModel() {

    private var mutableLoaderState: MutableLiveData<LiveDataEvent<LoaderData>> = MutableLiveData()
    val loaderState: LiveData<LiveDataEvent<LoaderData>>
        get() {
            return mutableLoaderState
        }

    private var mutableMainState: MutableLiveData<LiveDataEvent<MoviesData<Movie>>> = MutableLiveData()
    val mainState: LiveData<LiveDataEvent<MoviesData<Movie>>>
        get() {
            return mutableMainState
        }

    fun getPopularMovies() {
        viewModelScope.launch {
            mutableLoaderState.value = LiveDataEvent(LoaderData(LoaderStatus.SHOW))
            when (val result = withContext(Dispatchers.IO) { movieUseCase(1, CATEGORY_POPULAR) }) {
                is Result.Failure -> {
                    mutableLoaderState.postValue(LiveDataEvent(LoaderData(LoaderStatus.HIDE)))
                    mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.ERROR, error = result.exception)))
                }
                is Result.Success -> {
                    mutableLoaderState.postValue(LiveDataEvent(LoaderData(LoaderStatus.HIDE)))
                    mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.SUCCESSFUL, data = result.data)))
                }
            }
        }
    }
}

data class MoviesData<String>(var moviesStatus: MoviesStatus, var data: List<Movie>? = null, var error: Exception? = null)

enum class MoviesStatus {
    SUCCESSFUL,
    ERROR
}

data class LoaderData(var loaderStatus: LoaderStatus)

enum class LoaderStatus {
    SHOW,
    HIDE
}