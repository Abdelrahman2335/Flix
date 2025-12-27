package com.example.flix.app.home.presentation.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.flix.app.home.presentation.view.component.Appbar
import com.example.flix.app.home.presentation.view.component.HomeScreenContent

@Composable
fun HomeScreen(
    onMovieClick: (Int) -> Unit
) {
    // Create ViewModel using Hilt - this will be the single instance

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,

        topBar = { Appbar() },

        ) { innerPadding ->
        HomeScreenContent(
            modifier = Modifier.padding(innerPadding),
            onMovieClick = onMovieClick
        )

    }
}



