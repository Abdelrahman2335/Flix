package com.example.flix.movie.presentation.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.flix.movie.presentation.view.component.MovieScreenContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    onNavigateBack: () -> Unit
) {

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,

        ) {
        MovieScreenContent(
            modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
            onNavigateBack = onNavigateBack
        )
    }
}
