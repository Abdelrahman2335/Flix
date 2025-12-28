package com.example.flix.app.search.presentation.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flix.app.search.data.model.Result
import com.example.flix.app.search.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var searchResult by mutableStateOf<List<Result>>(emptyList())
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun searchMovie(query: String) {
        Log.d("SearchViewModel", "Starting to search for: $query")

        viewModelScope.launch {
            isLoading = true
            error = null
            try {

                val result = repository.searchMovie(query)
                searchResult = result.results

            } catch (e: Exception) {
                Log.e(
                    "SearchViewModel",
                    "Error search Movie: ${e.javaClass.simpleName} - ${e.message}",
                    e
                )
                error = "Failed to search movies: ${e.message}"

            } finally {
                isLoading = false
            }
        }
    }

}