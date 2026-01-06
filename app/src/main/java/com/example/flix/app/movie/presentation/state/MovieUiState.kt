package com.example.flix.app.movie.presentation.state

import com.example.flix.app.movie.data.model.cast.Cast
import com.example.flix.app.movie.data.model.movie.MovieModel

data class MovieUiState(
    val isLoading: Boolean = false,
    val movie: MovieModel? = null,
    val cast: List<Cast> = emptyList(),
    val error: String? = null
)
