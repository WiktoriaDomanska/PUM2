package com.example.lista1_composeshop

import kotlinx.serialization.Serializable

@Serializable
data object Home

@Serializable
data object Categories

@Serializable
data object Cart

@Serializable
data object Favorites

@Serializable
data class Details(val id: Int)

@Serializable
data object Profile

@Serializable
data object OrderHistory

@Serializable
data object Settings

@Serializable
data object Checkout

@Serializable
data object Address

@Serializable
data object Payment

@Serializable
data object Summary