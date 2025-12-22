package com.example.flix.app.home.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flix.app.home.core.api_service.RetrofitInstance
import com.example.flix.app.home.data.model.GenreResponse
import com.example.flix.app.home.data.model.Movie
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    init {
        Log.d("HomeViewModel", "ViewModel created")
    }

    var isLoading by mutableStateOf(false)
        private set

    var genreLoading by mutableStateOf(false)
        private set
    var selectedGenreId by mutableStateOf(28)
        private set

    var movieResponse by mutableStateOf<List<Movie>>(emptyList())
        private set

    var SearchedGenreResponse by mutableStateOf<List<Movie>>(emptyList())
        private set

    var genresResponses by mutableStateOf<GenreResponse>(GenreResponse(emptyList()))
        private set

    var error by mutableStateOf<String?>(null)
        private set

    private var genreMap by mutableStateOf<Map<Int, String>>(emptyMap())


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("HomeViewModel", "Unhandled exception in coroutine", throwable)
        error = "Error: ${throwable.message}"
        isLoading = false
    }

    init {
        viewModelScope.launch(exceptionHandler) {
            isLoading = true
            error = null
            try {
                loadingGenres()
                loadPopularMovies()
                searchByGenre(28)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Fatal error during initialization", e)
                error = "Failed to load data: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    private suspend fun loadingGenres() {
        Log.d("HomeViewModel", "Starting to load genres...")
        try {
            val response = RetrofitInstance.movieApi.getMovieGenres()
            genresResponses = response

            Log.d("HomeViewModel", "Genre API response received: $response")
            genreMap = response.genres.associate { it.id to it.name }
            Log.d("HomeViewModel", "Loaded ${genreMap.size} genres: $genreMap")

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
            val response = RetrofitInstance.movieApi.getPopularMovies(1)
            Log.d("HomeViewModel", "Movies API response received: ${response.results.size} results")
            movieResponse = response.results
            Log.d("HomeViewModel", "Loaded ${movieResponse.size} movies")
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
        selectedGenreId = genreId
        Log.d("HomeViewModel", "Starting to search by Genre with ID: $genreId")
        viewModelScope.launch(exceptionHandler) {
            genreLoading = true
            error = null
            try {
                val response = RetrofitInstance.movieApi.discoverMovies(genreId)
                SearchedGenreResponse = response.results
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
                error = "Failed to load movies for genre: ${e.message}"
            } finally {
                genreLoading = false
            }
        }

    }


    fun getImageUrl(posterPath: String?): String {
        return if (posterPath != null) {
            "https://image.tmdb.org/t/p/w500${posterPath}"
        } else {
            "" // Return empty string for null paths
        }
    }

    fun getGenreNames(movie: Movie): String {
        // Try to get genre names from full genre objects first (detail endpoint)
        if (!movie.genres.isNullOrEmpty()) {
            return movie.genres
                .take(3).joinToString(", ") { genreMap[it.id] ?: it.name }
                .ifEmpty { "Unknown" }
        }

        // Otherwise, use genre IDs (popular movies endpoint)
        if (!movie.genreIds.isNullOrEmpty()) {
            return movie.genreIds
                .take(3)
                .mapNotNull { genreMap[it] }
                .joinToString(", ")
                .ifEmpty { "Unknown" }
        }

        return "No genres"
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("HomeViewModel", "ViewModel cleared/destroyed")
    }
}