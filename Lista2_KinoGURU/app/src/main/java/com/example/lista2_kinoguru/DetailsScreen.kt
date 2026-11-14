package com.example.lista2_kinoguru

import androidx.benchmark.traceprocessor.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.time.TimeSource

@Composable
fun DetailsScreen(id: Int, onBack: () -> Unit) {
    var movieData by remember(id) {
        mutableStateOf<FullMovieDetails?>(null)
    }

    var isLoading by remember(id) {
        mutableStateOf(true)
    }

    var selectedMode by rememberSaveable(id) {
        mutableStateOf("Współbieżnie")
    }

    var usedMode by remember(id) {
        mutableStateOf("")
    }

    var loadingTime by remember(id) {
        mutableStateOf(0L)
    }

    val scope = rememberCoroutineScope()

    suspend fun loadData() {
        isLoading = true
        movieData = null

        val mode = selectedMode
        val start = TimeSource.Monotonic.markNow()

        val result = if (mode == "Współbieżnie") {
            loadMovieDetails(id)
        } else {
            loadMovieDetailsSequential(id)
        }

        val time = start.elapsedNow().inWholeMilliseconds

        movieData = result
        loadingTime = time
        usedMode = mode
        isLoading = false
    }

    LaunchedEffect(id) {
        loadData()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = onBack) {
            Text("Wstecz")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tryb pobierania",
            style = MaterialTheme.typography.titleMedium
        )

        Row {
            Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedMode == "Współbieżnie",
                    onClick = {selectedMode = "Współbieżnie"}
                )
                Text("Współbieżnie")
            }
        }

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            RadioButton(
                selected = selectedMode == "Sekwencyjnie",
                onClick = {selectedMode = "Sekwencyjnie"}
            )
            Text("Sekwencyjnie")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                scope.launch {
                    loadData()
                }
            },
            enabled = !isLoading
        ) {
            Text("Wczytaj ponownie")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
            Text("Pobieranie danych...")
        } else {
            val data = movieData

            if (data != null) {
                Text("Tryb: $usedMode")
                Text("Czas: $loadingTime ms")

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    item {
                        Text(
                            text = data.details.movie.title,
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Reżyser: ${data.details.movie.director}")
                        Text("Rok: ${data.details.movie.year}")
                        Text("Gatunek: ${data.details.movie.genre}")

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Opis",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(data.details.description)

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = "Recenzje",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    items(data.reviews) {review ->
                        Text("${review.author}: ${review.text}")
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Podobne filmy",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }

                    items(data.similarMovies) {movie ->
                        Text("${movie.title} (${movie.year})")

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}