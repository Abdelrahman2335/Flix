package com.example.flix.app.search.presentation.state

import com.example.flix.app.search.data.model.Result

data class SearchUiState(
    val isLoading: Boolean = false,
    val searchResults: List<Result> = emptyList(),
    val query: String = "",
    val error: String? = null
)

