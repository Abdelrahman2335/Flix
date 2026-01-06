package com.example.flix.app.movie.presentation.view_model

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.flix.app.movie.data.repository.MovieRepository
import com.example.flix.app.movie.presentation.state.MovieUiState
import com.example.flix.core.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val movieId: Int = savedStateHandle.toRoute<Movie>().movieId

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                getMovie(movieId)
                getMovieTrailer(movieId)
                getCast(movieId)
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Fatal error during initialization", e)
                _uiState.update { it.copy(error = "Failed to load data: ${e.message}") }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }

        }
    }

    private suspend fun getMovie(movieId: Int) {
        Log.d("MovieViewModel", "Starting to get Movie: $movieId")
        try {
            val response = repository.getMovieDetails(movieId)
            _uiState.update { it.copy(movie = response) }

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
            _uiState.update { it.copy(cast = response.cast) }

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