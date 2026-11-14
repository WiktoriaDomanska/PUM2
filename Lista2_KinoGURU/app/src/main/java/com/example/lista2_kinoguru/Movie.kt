package com.example.lista2_kinoguru

data class Movie(
    val id: Int,
    val title: String,
    val director: String,
    val year: Int,
    val genre: String
)

data class Review(
    val author: String,
    val text: String
)

data class MovieDetails(
    val movie: Movie,
    val description: String
)