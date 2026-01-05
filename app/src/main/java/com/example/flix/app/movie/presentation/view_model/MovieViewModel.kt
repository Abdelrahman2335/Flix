package com.example.flix.app.movie.presentation.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.flix.app.movie.data.model.cast.Cast
import com.example.flix.app.movie.data.model.movie.MovieModel
import com.example.flix.app.movie.data.repository.MovieRepository
import com.example.flix.core.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val movieId: Int = savedStateHandle.toRoute<Movie>().movieId

    var isLoading by mutableStateOf(false)

    var movie by mutableStateOf<MovieModel?>(null)
        private set

    var cast by mutableStateOf<List<Cast>>(emptyList())
        private set

    var error by mutableStateOf<String?>(null)
        private set


    init {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                getMovie(movieId)
                getMovieTrailer(movieId)
                getCast(movieId)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Fatal error during initialization", e)
                error = "Failed to load data: ${e.message}"
            } finally {
                isLoading = false
            }

        }
    }

    private suspend fun getMovie(movieId: Int) {
        Log.d("MovieViewModel", "Starting to get Movie: $movieId")
        try {
            val response = repository.getMovieDetails(movieId)
            movie = response

            Log.d("MovieViewModel", "Movie API response received: $response")

        } catch (e: Exception) {
            Log.e(
                "MovieViewModel",
                "Error loading Movie Details: ${e.javaClass.simpleName} - ${e.message}",
                e
            )
            throw e
        }
    }


    private suspend fun getMovieTrailer(id: Int) {
        Log.d("MovieViewModel", "Starting to load movie trailer...")
        try {
            val response = repository.getMovieTrailer(id)

            Log.d("MovieViewModel", "Movie Trailer API response received: $response")

        } catch (e: Exception) {
            Log.e(
                "MovieViewModel",
                "Error loading Movie Trailer: ${e.javaClass.simpleName} - ${e.message}",
                e
            )
            throw e
        }
    }

    private suspend fun getCast(id: Int) {
        Log.d("MovieViewModel", "Starting to load cast...")
        try {
            val response = repository.getCast(id)
            cast = response.cast

            Log.d("MovieViewModel", "Cast API response received: $response")

        } catch (e: Exception) {
            Log.e(
                "MovieViewModel",
                "Error loading Cast: ${e.javaClass.simpleName} - ${e.message}",
                e
            )
            throw e
        }
    }
}