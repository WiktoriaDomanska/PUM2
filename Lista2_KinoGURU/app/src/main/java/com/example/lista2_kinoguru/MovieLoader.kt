package com.example.lista2_kinoguru

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

data class FullMovieDetails(
    val details: MovieDetails,
    val reviews: List<Review>,
    val similarMovies: List<Movie>
)

suspend fun loadMovieDetails(movieId: Int): FullMovieDetails = coroutineScope {
    val detailsTask = async {fetchMovieDetails(movieId)}
    val reviewsTask = async {fetchMovieReviews(movieId)}
    val similarTask = async {fetchSimilarMovies(movieId)}

    val details = detailsTask.await()
    val reviews = reviewsTask.await()
    val similar = similarTask.await()

    FullMovieDetails(details, reviews, similar)
}

suspend fun loadMovieDetailsSequential(movieId: Int): FullMovieDetails {
    val details = fetchMovieDetails(movieId)
    val reviews = fetchMovieReviews(movieId)
    val similar = fetchSimilarMovies(movieId)

    return FullMovieDetails(details, reviews, similar)
}