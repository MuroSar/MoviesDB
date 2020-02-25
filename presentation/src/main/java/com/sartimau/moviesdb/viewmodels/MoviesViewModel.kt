package com.sartimau.moviesdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sartimau.domain.entities.MoviePage
import com.sartimau.domain.usecases.GetMovieUseCase
import com.sartimau.domain.utils.Constants.CATEGORY_POPULAR
import com.sartimau.domain.utils.Constants.CATEGORY_TOP_RATED
import com.sartimau.domain.utils.Constants.CATEGORY_UPCOMING
import com.sartimau.moviesdb.utils.LiveDataEvent
import com.sartimau.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MoviesViewModel(val getMovieUseCase: GetMovieUseCase) : ViewModel() {

    private var mutableLoaderState: MutableLiveData<LiveDataEvent<LoaderData>> = MutableLiveData()
    val loaderState: LiveData<LiveDataEvent<LoaderData>>
        get() {
            return mutableLoaderState
        }

    private var mutableMainState: MutableLiveData<LiveDataEvent<MoviesData<MoviePage>>> = MutableLiveData()
    val mainState: LiveData<LiveDataEvent<MoviesData<MoviePage>>>
        get() {
            return mutableMainState
        }

    fun getPopularMovies(networkAvailable: Boolean, page: Int) = viewModelScope.launch {
        mutableLoaderState.value = LiveDataEvent(LoaderData(LoaderStatus.SHOW))
        when (val result = withContext(Dispatchers.IO) { getMovieUseCase(page, CATEGORY_POPULAR, networkAvailable) }) {
            is Result.Failure -> {
                mutableLoaderState.postValue(LiveDataEvent(LoaderData(LoaderStatus.HIDE)))
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.ERROR_FIRST_PAGE, error = result.exception)))
            }
            is Result.Success -> {
                mutableLoaderState.postValue(LiveDataEvent(LoaderData(LoaderStatus.HIDE)))
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.SUCCESSFUL_FIRST_PAGE, data = result.data)))
            }
        }
    }

    fun getPopularMoviesNextPage(networkAvailable: Boolean, page: Int) = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) { getMovieUseCase(page, CATEGORY_POPULAR, networkAvailable) }) {
            is Result.Failure -> {
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.ERROR_NEXT_PAGE, error = result.exception)))
            }
            is Result.Success -> {
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.SUCCESSFUL_NEXT_PAGE, data = result.data)))
            }
        }
    }

    fun getTopRatedMovies(networkAvailable: Boolean, page: Int) = viewModelScope.launch {
        mutableLoaderState.value = LiveDataEvent(LoaderData(LoaderStatus.SHOW))
        when (val result = withContext(Dispatchers.IO) { getMovieUseCase(page, CATEGORY_TOP_RATED, networkAvailable) }) {
            is Result.Failure -> {
                mutableLoaderState.postValue(LiveDataEvent(LoaderData(LoaderStatus.HIDE)))
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.ERROR_FIRST_PAGE, error = result.exception)))
            }
            is Result.Success -> {
                mutableLoaderState.postValue(LiveDataEvent(LoaderData(LoaderStatus.HIDE)))
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.SUCCESSFUL_FIRST_PAGE, data = result.data)))
            }
        }
    }

    fun getTopRatedMoviesNextPage(networkAvailable: Boolean, page: Int) = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) { getMovieUseCase(page, CATEGORY_TOP_RATED, networkAvailable) }) {
            is Result.Failure -> {
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.ERROR_NEXT_PAGE, error = result.exception)))
            }
            is Result.Success -> {
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.SUCCESSFUL_NEXT_PAGE, data = result.data)))
            }
        }
    }

    fun getUpcomingMovies(networkAvailable: Boolean, page: Int) = viewModelScope.launch {
        mutableLoaderState.value = LiveDataEvent(LoaderData(LoaderStatus.SHOW))
        when (val result = withContext(Dispatchers.IO) { getMovieUseCase(page, CATEGORY_UPCOMING, networkAvailable) }) {
            is Result.Failure -> {
                mutableLoaderState.postValue(LiveDataEvent(LoaderData(LoaderStatus.HIDE)))
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.ERROR_FIRST_PAGE, error = result.exception)))
            }
            is Result.Success -> {
                mutableLoaderState.postValue(LiveDataEvent(LoaderData(LoaderStatus.HIDE)))
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.SUCCESSFUL_FIRST_PAGE, data = result.data)))
            }
        }
    }

    fun getUpcomingMoviesNextPage(networkAvailable: Boolean, page: Int) = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) { getMovieUseCase(page, CATEGORY_UPCOMING, networkAvailable) }) {
            is Result.Failure -> {
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.ERROR_NEXT_PAGE, error = result.exception)))
            }
            is Result.Success -> {
                mutableMainState.postValue(LiveDataEvent(MoviesData(moviesStatus = MoviesStatus.SUCCESSFUL_NEXT_PAGE, data = result.data)))
            }
        }
    }
}

data class MoviesData<MoviePage>(var moviesStatus: MoviesStatus, var data: MoviePage? = null, var error: Exception? = null)

enum class MoviesStatus {
    SUCCESSFUL_FIRST_PAGE,
    ERROR_FIRST_PAGE,
    SUCCESSFUL_NEXT_PAGE,
    ERROR_NEXT_PAGE
}

data class LoaderData(var loaderStatus: LoaderStatus)

enum class LoaderStatus {
    SHOW,
    HIDE
}