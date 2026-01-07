package com.example.flix.movie.presentation.state

import com.example.flix.movie.data.model.cast.Cast
import com.example.flix.movie.data.model.movie.MovieModel

data class MovieUiState(
    val isLoading: Boolean = false,
    val movie: MovieModel? = null,
    val cast: List<Cast> = emptyList(),
    val error: String? = null,
    val isNavigating: Boolean = false
)
