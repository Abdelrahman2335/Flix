package com.example.flix.movie.presentation.event

sealed interface MovieUiEvent {
    data object NavigateBack : MovieUiEvent
}

