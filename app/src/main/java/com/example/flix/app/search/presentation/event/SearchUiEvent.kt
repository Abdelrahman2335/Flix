package com.example.flix.app.search.presentation.event

sealed interface SearchUiEvent {
    data class SearchQueryChanged(val query: String) : SearchUiEvent
    data class MovieClicked(val movieId: Int) : SearchUiEvent
}

