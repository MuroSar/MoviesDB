package com.sartimau.moviesdb.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.whenever
import com.sartimau.domain.entities.MoviePage
import com.sartimau.domain.usecases.GetMovieUseCase
import com.sartimau.domain.utils.Constants.CATEGORY_POPULAR
import com.sartimau.domain.utils.Constants.CATEGORY_TOP_RATED
import com.sartimau.domain.utils.Constants.CATEGORY_UPCOMING
import com.sartimau.domain.utils.Result
import com.sartimau.moviesdb.testObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.Exception

class MoviesViewModelTest {

    @ObsoleteCoroutinesApi
    private var mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MoviesViewModel

    @Mock private lateinit var getMovieUseCase: GetMovieUseCase
    @Mock private lateinit var moviesValidResult: Result.Success<MoviePage>
    @Mock private lateinit var moviesInvalidResult: Result.Failure
    @Mock private lateinit var moviePage: MoviePage
    @Mock private lateinit var exception: Exception

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.initMocks(this)

        whenever(moviesValidResult.data).thenReturn(moviePage)
        whenever(moviesInvalidResult.exception).thenReturn(exception)

        viewModel = MoviesViewModel(getMovieUseCase)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun after() {
        mainThreadSurrogate.close()
        Dispatchers.resetMain()
    }

    @Test
    fun getPopularMoviesSuccess() {
        runBlocking {
            val loaderState = viewModel.loaderState.testObserver()
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(1, CATEGORY_POPULAR, true)).thenReturn(moviesValidResult)

            viewModel.getPopularMovies(true, 1).join()

            assertEquals(LoaderStatus.SHOW, loaderState.observedValues[0]?.peekContent()?.loaderStatus)
            assertEquals(LoaderStatus.HIDE, loaderState.observedValues[1]?.peekContent()?.loaderStatus)
            assertEquals(MoviesData(MoviesStatus.SUCCESSFUL_FIRST_PAGE, moviesValidResult.data), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getPopularMoviesError() {
        runBlocking {
            val loaderState = viewModel.loaderState.testObserver()
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(1, CATEGORY_POPULAR, true)).thenReturn(moviesInvalidResult)

            viewModel.getPopularMovies(true, 1).join()

            assertEquals(LoaderStatus.SHOW, loaderState.observedValues[0]?.peekContent()?.loaderStatus)
            assertEquals(LoaderStatus.HIDE, loaderState.observedValues[1]?.peekContent()?.loaderStatus)
            assertEquals(MoviesData(MoviesStatus.ERROR_FIRST_PAGE, null, moviesInvalidResult.exception), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getPopularNextMoviesSuccess() {
        runBlocking {
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(SOME_PAGE, CATEGORY_POPULAR, true)).thenReturn(moviesValidResult)

            viewModel.getPopularMoviesNextPage(true, SOME_PAGE).join()

            assertEquals(MoviesData(MoviesStatus.SUCCESSFUL_NEXT_PAGE, moviesValidResult.data), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getPopularNextMoviesError() {
        runBlocking {
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(SOME_PAGE, CATEGORY_POPULAR, true)).thenReturn(moviesInvalidResult)

            viewModel.getPopularMoviesNextPage(true, SOME_PAGE).join()

            assertEquals(MoviesData(MoviesStatus.ERROR_NEXT_PAGE, null, moviesInvalidResult.exception), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getTopRatedMoviesSuccess() {
        runBlocking {
            val loaderState = viewModel.loaderState.testObserver()
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(1, CATEGORY_TOP_RATED, true)).thenReturn(moviesValidResult)

            viewModel.getTopRatedMovies(true, 1).join()

            assertEquals(LoaderStatus.SHOW, loaderState.observedValues[0]?.peekContent()?.loaderStatus)
            assertEquals(LoaderStatus.HIDE, loaderState.observedValues[1]?.peekContent()?.loaderStatus)
            assertEquals(MoviesData(MoviesStatus.SUCCESSFUL_FIRST_PAGE, moviesValidResult.data), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getTopRatedMoviesError() {
        runBlocking {
            val loaderState = viewModel.loaderState.testObserver()
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(1, CATEGORY_TOP_RATED, true)).thenReturn(moviesInvalidResult)

            viewModel.getTopRatedMovies(true, 1).join()

            assertEquals(LoaderStatus.SHOW, loaderState.observedValues[0]?.peekContent()?.loaderStatus)
            assertEquals(LoaderStatus.HIDE, loaderState.observedValues[1]?.peekContent()?.loaderStatus)
            assertEquals(MoviesData(MoviesStatus.ERROR_FIRST_PAGE, null, moviesInvalidResult.exception), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getTopRatedNextMoviesSuccess() {
        runBlocking {
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(SOME_PAGE, CATEGORY_TOP_RATED, true)).thenReturn(moviesValidResult)

            viewModel.getTopRatedMoviesNextPage(true, SOME_PAGE).join()

            assertEquals(MoviesData(MoviesStatus.SUCCESSFUL_NEXT_PAGE, moviesValidResult.data), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getTopRatedNextMoviesError() {
        runBlocking {
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(SOME_PAGE, CATEGORY_TOP_RATED, true)).thenReturn(moviesInvalidResult)

            viewModel.getTopRatedMoviesNextPage(true, SOME_PAGE).join()

            assertEquals(MoviesData(MoviesStatus.ERROR_NEXT_PAGE, null, moviesInvalidResult.exception), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getUpcomingMoviesSuccess() {
        runBlocking {
            val loaderState = viewModel.loaderState.testObserver()
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(1, CATEGORY_UPCOMING, true)).thenReturn(moviesValidResult)

            viewModel.getUpcomingMovies(true, 1).join()

            assertEquals(LoaderStatus.SHOW, loaderState.observedValues[0]?.peekContent()?.loaderStatus)
            assertEquals(LoaderStatus.HIDE, loaderState.observedValues[1]?.peekContent()?.loaderStatus)
            assertEquals(MoviesData(MoviesStatus.SUCCESSFUL_FIRST_PAGE, moviesValidResult.data), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getUpcomingMoviesError() {
        runBlocking {
            val loaderState = viewModel.loaderState.testObserver()
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(1, CATEGORY_UPCOMING, true)).thenReturn(moviesInvalidResult)

            viewModel.getUpcomingMovies(true, 1).join()

            assertEquals(LoaderStatus.SHOW, loaderState.observedValues[0]?.peekContent()?.loaderStatus)
            assertEquals(LoaderStatus.HIDE, loaderState.observedValues[1]?.peekContent()?.loaderStatus)
            assertEquals(MoviesData(MoviesStatus.ERROR_FIRST_PAGE, null, moviesInvalidResult.exception), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getUpcomingNextMoviesSuccess() {
        runBlocking {
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(SOME_PAGE, CATEGORY_UPCOMING, true)).thenReturn(moviesValidResult)

            viewModel.getUpcomingMoviesNextPage(true, SOME_PAGE).join()

            assertEquals(MoviesData(MoviesStatus.SUCCESSFUL_NEXT_PAGE, moviesValidResult.data), mainState.observedValues[0]?.peekContent())
        }
    }

    @Test
    fun getUpcomingNextMoviesError() {
        runBlocking {
            val mainState = viewModel.mainState.testObserver()

            whenever(getMovieUseCase(SOME_PAGE, CATEGORY_UPCOMING, true)).thenReturn(moviesInvalidResult)

            viewModel.getUpcomingMoviesNextPage(true, SOME_PAGE).join()

            assertEquals(MoviesData(MoviesStatus.ERROR_NEXT_PAGE, null, moviesInvalidResult.exception), mainState.observedValues[0]?.peekContent())
        }
    }

    companion object {
        private const val SOME_PAGE = 5
    }
}
