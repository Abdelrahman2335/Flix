package com.example.flix.search.presentation.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flix.search.data.repository.SearchRepository
import com.example.flix.search.presentation.event.SearchUiEvent
import com.example.flix.search.presentation.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        observeSearchQuery()
    }

    fun onEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.SearchQueryChanged -> {
                _uiState.update { it.copy(query = event.query) }
            }

            is SearchUiEvent.MovieClicked -> {
                // Handle movie click if needed (e.g., analytics)
                Log.d("SearchViewModel", "Movie clicked: ${event.movieId}")
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            _uiState
                .debounce(500) // 500ms debounce
                .distinctUntilChanged { old, new -> old.query == new.query }
                .filter { it.query.isNotEmpty() }
                .collect { state ->
                    searchMovie(state.query)
                }
        }
    }

    private suspend fun searchMovie(query: String) {
        Log.d("SearchViewModel", "Starting to search for: $query")

        _uiState.update { it.copy(isLoading = true, error = null) }

        try {
            val result = repository.searchMovie(query)
            _uiState.update { it.copy(searchResults = result.results, isLoading = false) }

        } catch (e: Exception) {
            Log.e(
                "SearchViewModel",
                "Error search Movie: ${e.javaClass.simpleName} - ${e.message}",
                e
            )
            _uiState.update {
                it.copy(
                    error = "Failed to search movies: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

}