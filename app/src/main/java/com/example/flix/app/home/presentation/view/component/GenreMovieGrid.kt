package com.example.flix.app.home.presentation.view.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.flix.app.home.presentation.viewmodel.HomeViewModel

@Composable
fun GenreMovieGrid(
    rowIndex: Int,
    homeViewModel: HomeViewModel
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 11.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val rowItems = homeViewModel.SearchedGenreResponse.chunked(2)[rowIndex]

        rowItems.forEach { movie ->

            AsyncImage(
                model = homeViewModel.getImageUrl(movie.posterPath),
                contentDescription = movie.title,
                modifier = Modifier
                    .weight(1f)
                    .height(217.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
                onError = {
                    Log.e(
                        "HomeScreenContent",
                        "Error loading image for ${movie.title}: ${it.result.throwable}"
                    )
                },
                onSuccess = {
                    Log.d(
                        "HomeScreenContent",
                        "Successfully loaded image for ${movie.title}"
                    )
                }
            )

        }
        // Add spacer for last row if odd number of items
        if (rowItems.size == 1) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}