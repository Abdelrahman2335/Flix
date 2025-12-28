package com.example.flix.app.search.presentation.view.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flix.R


@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit
) {

    TextField(
        modifier = modifier
            .width(345.dp)
            .padding(16.dp),


        onValueChange = onQueryChange,
        value = query,
        placeholder = { Text("Search") },
        shape = RoundedCornerShape(14.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                tint = Color(0xFFB9C1D9),
                contentDescription = "Search Field",
            )
        },
        trailingIcon = {

//            IconButton(onClick = { text = "" }) {
//                Icon(
//                    imageVector = Icons.Filled.Clear,
//                    tint = Color(0xFFB9C1D9),
//                    contentDescription = "Clear"
//                )
//            }

        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF131316),
            unfocusedContainerColor = Color(0xFF131316),
            disabledContainerColor = Color(0xFF131316),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )

    )
}