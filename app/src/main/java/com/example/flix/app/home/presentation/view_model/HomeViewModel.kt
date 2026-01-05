package com.example.flix.app.home.presentation.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flix.app.home.data.model.PopularMovie
import com.example.flix.app.home.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("HomeViewModel", "Unhandled exception in coroutine", throwable)
        _uiState.update {
            it.copy(error = "Error: ${throwable.message}")
        }

        _uiState.update { it.copy(isLoading = false) }


    }

    init {
        viewModelScope.launch(exceptionHandler) {
            _uiState.update { it.copy(isLoading = true) }
            _uiState.update {
                it.copy(error = null)
            }
            try {
                loadingGenres()
                loadPopularMovies()
                searchByGenre(28)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Fatal error during initialization", e)
                _uiState.update {
                    it.copy(error = "Error: ${e.message}")
                }
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.SearchByGenre -> searchByGenre(event.genreId)
        }
    }

    private suspend fun loadingGenres() {
        Log.d("HomeViewModel", "Starting to load genres...")
        try {
            val response = repository.getMovieGenres()
            _uiState.update { it.copy(genres = response) }

            Log.d("HomeViewModel", "Genre API response received: $response")
            _uiState.update { state -> state.copy(genreMap = response.genres.associate { it.id to it.name }) }
            Log.d(
                "HomeViewModel",
                "Loaded ${_uiState.value.genreMap.size} genres: ${_uiState.value.genreMap}"
            )

        } catch (e: Exception) {
            Log.e(
                "HomeViewModel",
                "Error loading genres: ${e.javaClass.simpleName} - ${e.message}",
                e
            )
            throw e
        }
    }


    private suspend fun loadPopularMovies() {
        Log.d("HomeViewModel", "Starting to load popular movies...")
        try {
            val response = repository.getPopularMovies(1)
            Log.d("HomeViewModel", "Movies API response received: ${response.results.size} results")
            _uiState.update { it.copy(popularMovies = response.results) }
            Log.d("HomeViewModel", "Loaded ${_uiState.value.popularMovies.size} movies")
        } catch (e: Exception) {
            Log.e(
                "HomeViewModel",
                "Error loading movies: ${e.javaClass.simpleName} - ${e.message}",
                e
            )
            throw e
        }
    }

    fun searchByGenre(genreId: Int) {
        _uiState.update { it.copy(selectedGenreId = genreId) }
        Log.d("HomeViewModel", "Starting to search by Genre with ID: $genreId")
        viewModelScope.launch(exceptionHandler) {
            _uiState.update { it.copy(genreLoading = true, error = null) }
            try {
                val response = repository.discoverMovies(genreId)
                _uiState.update { it.copy(searchedGenreMovies = response.results) }
                Log.d(
                    "HomeViewModel",
                    "Genre API response received: ${response.results.size} results"
                )
            } catch (e: Exception) {
                Log.e(
                    "HomeViewModel",
                    "Error loading movies by genre: ${e.javaClass.simpleName} - ${e.message}",
                    e
                )
                _uiState.update { it.copy(error = "Failed to load movies for genre: ${e.message}") }
            } finally {
                _uiState.update { it.copy(genreLoading = false) }
            }
        }

    }


    fun getGenreNames(popularMovie: PopularMovie): String {
        val genreMap = _uiState.value.genreMap
        // Try to get genre names from full genre objects first (detail endpoint)
        if (!popularMovie.genres.isNullOrEmpty()) {
            return popularMovie.genres
                .take(3).joinToString(", ") { genreMap[it.id] ?: it.name }
                .ifEmpty { "Unknown" }
        }

        // Otherwise, use genre IDs (popular movies endpoint)
        if (!popularMovie.genreIds.isNullOrEmpty()) {
            return popularMovie.genreIds
                .take(3)
                .mapNotNull { genreMap[it] }
                .joinToString(", ")
                .ifEmpty { "Unknown" }
        }

        return "No genres"
    }
}
