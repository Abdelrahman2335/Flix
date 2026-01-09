package com.example.flix.movie.presentation.view_model

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.flix.core.Movie
import com.example.flix.core.util.HelperMethod
import com.example.flix.movie.data.repository.MovieRepository
import com.example.flix.movie.presentation.event.MovieUiEvent
import com.example.flix.movie.presentation.state.MovieUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
    private var movieId: Int? = null
    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState.asStateFlow()
    private val helperMethod = HelperMethod()


    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                movieId = savedStateHandle.toRoute<Movie>().movieId
                movieId?.let { id ->
                    getMovie(id)
                    getCast(id)
                    getVideos(id)
                }
            } catch (e: Exception) {
                Log.e("MovieViewModel", "Fatal error during initialization", e)
                _uiState.update { it.copy(error = "Failed to load data: ${e.message}") }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }


    fun onEvent(event: MovieUiEvent) {
        when (event) {
            is MovieUiEvent.NavigateBack -> {
                handleNavigateBack()
            }

            is MovieUiEvent.GetVideos -> getVideos(
                event.movieId,
                event.context,
                event.launchTrailer
            )
        }
    }

    private fun handleNavigateBack() {
        if (!_uiState.value.isNavigating) {
            _uiState.update { it.copy(isNavigating = true) }
            viewModelScope.launch {
                delay(500) // Prevent rapid clicks for 500ms
                _uiState.update { it.copy(isNavigating = false) }
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

    fun getVideos(movieId: Int, context: Context? = null, launchTrailer: Boolean = false) {
        Log.d("MovieViewModel", "Starting to load videos for movie: $movieId")
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoadingTrailer = true, error = null) }

                val response = repository.getMovieVideos(movieId)
                val trailers = response.results.filter { it.type == "Trailer" }
                val trailerKey = trailers.firstOrNull()?.key ?: ""
                _uiState.update {
                    it.copy(trailerKey = trailerKey)
                }
                if (launchTrailer && context != null && trailerKey.isNotEmpty()) {
                    helperMethod.launchTrailer(trailerKey, context)
                }
                Log.d(
                    "MovieViewModel",
                    "Loaded ${trailers.size} trailers out of ${response.results.size} videos"
                )
            } catch (e: Exception) {
                Log.e(
                    "MovieViewModel",
                    "Error loading videos: ${e.javaClass.simpleName} - ${e.message}",
                    e
                )
                _uiState.update { it.copy(error = e.message) }
            } finally {
                _uiState.update { it.copy(isLoadingTrailer = false) }
            }
        }
    }
}