package com.example.lista2_kinoguru

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(onMovieClick: (Int) -> Unit) {
    var movieList by remember { mutableStateOf<List<Movie>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        isLoading = true
        movieList = fetchPopularMovies()
        isLoading = false
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Kino GURU",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(movieList) {movie ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable {onMovieClick(movie.id)}
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text("${movie.year} | ${movie.genre}")
                            Text("Reżyser: ${movie.director}")
                        }
                    }
                }
            }
        }
    }
}