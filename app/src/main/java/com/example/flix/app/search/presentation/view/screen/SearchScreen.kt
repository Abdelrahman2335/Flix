package com.example.flix.app.search.presentation.view.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.flix.app.search.presentation.event.SearchUiEvent
import com.example.flix.app.search.presentation.view.component.SearchField
import com.example.flix.app.search.presentation.view_model.SearchViewModel
import com.example.flix.core.util.getImageUrl


@Composable
fun SearchScreen(
    onMovieClick: (Int) -> Unit
) {

    val searchViewModel = hiltViewModel<SearchViewModel>()
    val uiState by searchViewModel.uiState.collectAsState()

    // Remove the LaunchedEffect with debounce since it's now handled in ViewModel

    Scaffold(containerColor = Color(0xFF1F1F29)) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)

        ) {
            item {
                SearchField(
                    query = uiState.query,
                    onQueryChange = { newQuery ->
                        searchViewModel.onEvent(SearchUiEvent.SearchQueryChanged(newQuery))
                    }
                )
            }

            // Loading state
            if (uiState.isLoading) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                    ) {
                        CircularProgressIndicator(
                            color = Color.White
                        )
                    }
                }
            }
            // Empty state (no search yet)
            else if (uiState.query.isEmpty()) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                    ) {
                        Text(
                            text = "Search for a movie!",
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
            // No results found
            else if (!uiState.isLoading && uiState.searchResults.isEmpty()) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "No results found",
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Try searching with different keywords",
                                textAlign = TextAlign.Center,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
            // Display results
            else if (uiState.searchResults.isNotEmpty()) {
                items(uiState.searchResults.size) { count ->
                    val movie = uiState.searchResults[count]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                searchViewModel.onEvent(SearchUiEvent.MovieClicked(movie.id))
                                onMovieClick(movie.id)
                            }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp),
                        ) {
                            if (movie.poster_path != null) {
                                AsyncImage(
                                    model = getImageUrl(movie.poster_path),
                                    contentDescription = "Movie Image"
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(100.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No Image",
                                        color = Color.Gray,
                                        fontSize = 12.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                movie.title,
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = movie.overview,
                                color = Color.Gray,
                                fontSize = 14.sp,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
        }

    }
}

