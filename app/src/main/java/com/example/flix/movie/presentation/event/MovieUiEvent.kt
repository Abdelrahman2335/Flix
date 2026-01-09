package com.example.flix.movie.presentation.event

import android.content.Context

sealed interface MovieUiEvent {
    data object NavigateBack : MovieUiEvent
    data class GetVideos(
        val movieId: Int,
        val context: Context? = null,
        val launchTrailer: Boolean = false
    ) :
        MovieUiEvent
}

