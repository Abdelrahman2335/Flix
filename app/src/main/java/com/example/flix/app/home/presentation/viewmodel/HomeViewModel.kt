package com.example.flix.app.home.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flix.app.home.core.api_service.RetrofitInstance
import com.example.flix.app.home.data.model.Movie
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var movies by mutableStateOf<List<Movie>>(emptyList())
        private set

    var error by mutableStateOf<String?>(null)
        private set

    init {
        loadPopularMovies()
    }

    fun loadPopularMovies() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                Log.d("HomeViewModel", "Starting to load popular movies...")
                val response = RetrofitInstance.movieApi.getPopularMovies(1)
                movies = response.results
                Log.d("HomeViewModel", "Loaded ${movies.size} movies")
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading movies", e)
                error = e.message ?: "Unknown error"
            } finally {
                isLoading = false
            }
        }

    }
}