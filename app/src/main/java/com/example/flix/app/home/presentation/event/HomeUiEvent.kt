package com.example.flix.app.home.presentation.event

sealed interface HomeUiEvent {
    data class SearchByGenre(val genreId: Int) : HomeUiEvent

}