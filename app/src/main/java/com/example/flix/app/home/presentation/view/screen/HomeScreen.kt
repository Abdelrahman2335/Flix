package com.example.flix.app.home.presentation.view.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flix.app.home.presentation.view.component.Appbar
import com.example.flix.app.home.presentation.view.component.HomeScreenContent

@Preview
@Composable
fun HomeScreen() {


    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,

        topBar = { Appbar() },

        ) { innerPadding ->
        HomeScreenContent(Modifier.padding(innerPadding))

    }
}



