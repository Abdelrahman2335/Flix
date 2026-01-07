package com.example.flix.home.presentation.state

import com.example.flix.home.data.model.GenreResponse
import com.example.flix.home.data.model.PopularMovie

data class HomeUiState(
    val isLoading: Boolean = false,

    val genreLoading: Boolean = false,

    var selectedGenreId: Int = 28,
    val popularMovies: List<PopularMovie> = emptyList(),
    val searchedGenreMovies: List<PopularMovie> = emptyList(),
    val genres: GenreResponse = GenreResponse(emptyList()),
    val error: String? = null,
    val genreMap: Map<Int, String> = emptyMap()

)