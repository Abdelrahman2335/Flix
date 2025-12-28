package com.example.flix.app.home.presentation.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flix.R



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Appbar(
    onSearch: ()-> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            Color.Transparent
        ),
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = "Profile picture",
                Modifier
                    .padding(12.dp)
                    .size(44.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

        },
        title = {
            Column {
                Text(
                    "Welcome back!",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color(0xFFB9C1D9),
                    ),
                )
                Text(
                    "Abdelrahman",
                    fontWeight = FontWeight.W400,
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        },
        actions = { IconButton(
            onClick = {onSearch()},


        ){
            Icon(
                painter = painterResource(R.drawable.ic_search),
                tint = Color(0xFFB9C1D9),
                contentDescription = "Search Field",
            )
        }
        }

        )

}