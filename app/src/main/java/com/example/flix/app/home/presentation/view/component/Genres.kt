package com.example.flix.app.home.presentation.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flix.app.home.presentation.event.HomeUiEvent
import com.example.flix.app.home.presentation.view_model.HomeViewModel
import com.example.flix.core.data.model.Genre

@Composable
fun Genres(genre: Genre, isSelected: Boolean) {

    val homeViewModel = hiltViewModel<HomeViewModel>()

    Button(
        modifier = Modifier
            .padding(9.dp),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonColors(
            containerColor =
                if (isSelected)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onPrimary,
            contentColor = Color.White,
            disabledContainerColor = MaterialTheme.colorScheme.primary,
            disabledContentColor = MaterialTheme.colorScheme.primary
        ),
        onClick = {
            homeViewModel.onEvent(HomeUiEvent.SearchByGenre(genre.id))
        }
    ) {
        Text(genre.name, fontWeight = FontWeight.W500)
    }
}