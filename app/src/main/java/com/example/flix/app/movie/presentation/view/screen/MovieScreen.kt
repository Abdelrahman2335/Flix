package com.example.flix.app.movie.presentation.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.flix.app.movie.presentation.view.component.MovieScreenContent

@Composable
fun MovieScreen() {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        MovieScreenContent(modifier = Modifier.padding(bottom = it.calculateBottomPadding()))
    }
}

