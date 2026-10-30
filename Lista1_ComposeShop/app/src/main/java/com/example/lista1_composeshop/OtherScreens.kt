package com.example.lista1_composeshop

import android.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun CategoriesScreen(
    favoriteIds: List<Int>,
    onFavoriteClick: (Int) -> Unit,
    onDetailsClick: (Int) -> Unit
) {
    var selectedCategory by rememberSaveable() {
        mutableStateOf("Wszystkie")
    }

    val categories = listOf("Wszystkie", "Elektronika", "Akcesoria")

    val filteredProducts = if (selectedCategory == "Wszystkie") {
        products
    } else {
        products.filter { it.category == selectedCategory}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Kategorie",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        categories.forEach { category ->
            Button(
                onClick = { selectedCategory = category},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(category)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn() {
            items(filteredProducts) { product ->
                ProductItem(
                    product = product,
                    isFavorite = product.id in favoriteIds,
                    onFavoriteClick = { onFavoriteClick(product.id)},
                    onDetailsClick = { onDetailsClick(product.id)}
                )
            }
        }
    }
}

@Composable
fun CartScreen(onCheckoutClick: () -> Unit) {
    val cartProducts = products.take(2)
    val totalPrice = cartProducts.sumOf { it.price}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Twój koszyk",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        cartProducts.forEach { product ->
            Text(
                text = "${product.name} - ${product.price} zł",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        HorizontalDivider()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Razem: $totalPrice zł",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCheckoutClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Przejdź do zamówienia")
        }
    }
}

@Composable
fun FavoritesScreen(
    favoriteIds: List<Int>,
    onFavoriteClick: (Int) -> Unit,
    onDetailsClick: (Int) -> Unit
) {
    val favoriteProducts = products.filter {
        it.id in favoriteIds
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Ulubione",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteProducts.isEmpty()) {
            Text("Nie masz jeszcze ulubionych produktów")
        } else {
            LazyColumn {
                items(favoriteProducts) { product ->
                    ProductItem(
                        product = product,
                        isFavorite = true,
                        onFavoriteClick = {
                            onFavoriteClick(product.id)
                        },
                        onDetailsClick = { onDetailsClick(product.id) }
                    )
                }
            }
        }
    }
}