package com.example.flix.app.movie.presentation.view.screen

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.flix.app.movie.presentation.view.component.MovieScreenContent

@Composable
fun MovieScreen() {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        MovieScreenContent()
    }
}

