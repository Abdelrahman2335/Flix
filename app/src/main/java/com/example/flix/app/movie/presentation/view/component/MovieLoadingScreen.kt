package com.example.flix.app.movie.presentation.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.flix.core.util.shimmerEffect


@Composable
fun MovieLoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .shimmerEffect()
        )
        Spacer(Modifier.height(12.dp))
        Row {
            Box(
                modifier = Modifier
                    .size(77.dp, 60.dp)
                    .padding(9.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .shimmerEffect()

            )

            Box(
                modifier = Modifier
                    .size(77.dp, 60.dp)
                    .padding(9.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .shimmerEffect()

            )

            Box(
                modifier = Modifier
                    .size(77.dp, 60.dp)
                    .padding(9.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .shimmerEffect()

            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .padding(start = 6.dp, top = 14.dp, bottom = 14.dp)
                .height(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(6.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp))

                .shimmerEffect()

        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(6.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .shimmerEffect()

        )
        Spacer(Modifier.height(24.dp))

        Row {
            Box(
                modifier = Modifier
                    .size(112.dp)
                    .padding(9.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .shimmerEffect()

            )

            Box(
                modifier = Modifier
                    .size(112.dp)
                    .padding(9.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .shimmerEffect()

            )

            Box(
                modifier = Modifier
                    .size(112.dp)
                    .padding(9.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .shimmerEffect()

            )

            Box(
                modifier = Modifier
                    .size(112.dp)
                    .padding(9.dp)
                    .clip(RoundedCornerShape(9.dp))
                    .shimmerEffect()

            )

        }

    }
}