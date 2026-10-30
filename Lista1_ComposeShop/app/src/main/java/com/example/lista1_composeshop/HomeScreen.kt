package com.example.lista1_composeshop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    favoriteIds: List<Int>,
    onFavoriteClick: (Int) -> Unit,
    onDetailsClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Witaj w ComposeShop",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(products) { product ->
                ProductItem(
                    product = product,
                    isFavorite = product.id in favoriteIds,
                    onFavoriteClick = { onFavoriteClick(product.id)},
                    onDetailsClick = { onDetailsClick(product.id)})
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onDetailsClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onDetailsClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text("Cena: ${product.price} zł")
            Text("Kategoria: ${product.category}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = onFavoriteClick) {
                Text(
                    if (isFavorite) "Usuń z ulubionych"
                    else "Dodaj do ulubionych"
                )
            }
        }
    }
}