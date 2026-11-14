package com.example.lista2_kinoguru

import kotlinx.coroutines.delay

suspend fun fetchPopularMovies(): List<Movie> {
    delay(2000)
    return movies
}

suspend fun fetchMovieDetails(movieId: Int): MovieDetails {
    delay(1500)
    val movie = movies.first {it.id == movieId}
    val description = descriptions[movieId] ?: ""
    return MovieDetails(movie, description)
}

suspend fun fetchMovieReviews(movieId: Int): List<Review> {
    delay(3000)
    return reviews[movieId] ?: emptyList()
}

suspend fun fetchSimilarMovies(movieId: Int): List<Movie> {
    delay(2000)
    val ids = similarMovies[movieId] ?: emptyList()
    return movies.filter {it.id in ids && it.id != movieId}
}