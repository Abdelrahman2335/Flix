package com.example.flix.app.home.presentation.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flix.R

@Composable
fun RatingCompose(voteAverage: Double, fontWeight: FontWeight) {
    Row {
        Image(
            painter =
                painterResource(R.drawable.ic_star),
            contentDescription = "Movie Rating"
        )
        Text(
            String.format("%.1f", voteAverage),
            modifier = Modifier.padding(start = 4.dp),
            color = Color.White,
            fontWeight = fontWeight
        )
    }
}