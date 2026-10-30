package com.example.lista1_composeshop

data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val category: String
)

val products = listOf(
    Product(1, "Słuchawki", 149, "Elektronika"),
    Product(2, "Klawiatura", 199, "Elektronika"),
    Product(3, "Mysz komputerowa", 89, "Elektronika"),
    Product(4, "Notes A5", 24, "Akcesoria"),
    Product(5, "Kubek", 44, "Akcesoria"),
    Product(6, "Długopis", 8, "Akcesoria")
)