package com.example.flix.home.presentation.view.component

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.flix.core.util.getImageUrl
import com.example.flix.home.data.model.PopularMovie

@Composable
fun GenreMovieGrid(popularMovie: PopularMovie, onMovieClick: (Int) -> Unit) {


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .clickable(onClick = { onMovieClick(popularMovie.id) })

    ) {
        AsyncImage(
            model = getImageUrl(popularMovie.posterPath),
            contentDescription = popularMovie.title,
            modifier = Modifier
                .height(217.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            onError = {
                Log.e(
                    "HomeScreenContent",
                    "Error loading image for ${popularMovie.title}: ${it.result.throwable}"
                )
            },
            onSuccess = {
                Log.d(
                    "HomeScreenContent",
                    "Successfully loaded image for ${popularMovie.title}"
                )
            }
        )

        Text(
            textAlign = TextAlign.Center,
            text = popularMovie.title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}