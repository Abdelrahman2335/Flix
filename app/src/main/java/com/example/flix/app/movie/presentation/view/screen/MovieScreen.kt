package com.example.flix.app.movie.presentation.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.flix.app.home.presentation.viewmodel.HomeViewModel
import com.example.flix.app.movie.presentation.view.component.MovieScreenContent

@Composable
fun MovieScreen(
    movieId: String,
    homeViewModel: HomeViewModel,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        MovieScreenContent(
            Modifier.padding(it),
            movie = movie, genre = genre, homeViewModel = homeViewModel,
        )
    }
}

