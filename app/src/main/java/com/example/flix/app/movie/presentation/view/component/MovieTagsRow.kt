package com.example.flix.app.movie.presentation.view.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flix.app.home.presentation.view.component.RatingCompose
import com.example.flix.app.movie.data.model.movie.MovieModel

@Composable
fun MovieTags(
    movie: MovieModel,
) {
    val genre = movie.genres.get(index = 0).name
    Row {
        if (movie.adult)
            Button(
                modifier = Modifier
                    .padding(9.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonColors(
                    containerColor = Color(0xFF312F35),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF312F35),
                    disabledContentColor = Color.White
                ),
                enabled = false,
                onClick = {}
            ) {
                Text("+18", fontWeight = FontWeight.Bold)
            }
        Button(
            modifier = Modifier
                .padding(9.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonColors(
                containerColor = Color(0xFF312F35),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF312F35),
                disabledContentColor = Color.White
            ),
            enabled = false,
            onClick = {}
        ) {
            Text(genre, fontWeight = FontWeight.Bold)
        }
        Button(
            modifier = Modifier
                .padding(9.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonColors(
                containerColor = Color(0xFF312F35),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFF312F35),
                disabledContentColor = Color.White
            ),
            enabled = false,
            onClick = {}
        ) {
            RatingCompose(movie.voteAverage, FontWeight.Bold)
        }

    }
}