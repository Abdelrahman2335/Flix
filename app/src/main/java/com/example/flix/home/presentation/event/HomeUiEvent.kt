package com.example.flix.home.presentation.event

sealed interface HomeUiEvent {
    data class SearchByGenre(val genreId: Int) : HomeUiEvent

}