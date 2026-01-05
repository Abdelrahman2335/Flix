package com.example.flix.app.home.presentation.view_model

sealed interface HomeUiEvent {
    data class SearchByGenre(val genreId: Int) : HomeUiEvent

}