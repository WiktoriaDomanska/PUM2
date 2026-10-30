package com.example.lista1_composeshop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@Composable
fun AddressScreen(
    name: String,
    street: String,
    postalCode: String,
    city: String,
    onNameChange: (String) -> Unit,
    onStreetChange: (String) -> Unit,
    onPostalCodeChange: (String) -> Unit,
    onCityChange: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    var showError by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Adres dostawy", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Imię i nazwisko") }
        )

        OutlinedTextField(
            value = street,
            onValueChange = onStreetChange,
            label = { Text("Ulica i numer") }
        )

        OutlinedTextField(
            value = postalCode,
            onValueChange = onPostalCodeChange,
            label = { Text("Kod pocztowy") }
        )

        OutlinedTextField(
            value = city,
            onValueChange = onCityChange,
            label = { Text("Miasto") }
        )

        if (showError) {
            Text(
                text = "Uzupełnij wszystkie pola adresu",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            if (name.trim().isNotEmpty() &&
                street.trim().isNotEmpty() &&
                postalCode.trim().isNotEmpty() &&
                city.trim().isNotEmpty()
            ) {
                showError = false
                onNext()
            } else {
                showError = true
            }
        }) {
            Text("Dalej")
        }

        TextButton(onClick = onBack) {
            Text("Wstecz")
        }
    }
}



@Composable
fun PaymentScreen(
    selectedPayment: String,
    onPaymentChange: (String) -> Unit,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    val paymentMethods = listOf("Karta", "BLIK", "Przelew")
    var showError by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Metoda płatności", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        paymentMethods.forEach { method ->
            Row {
                RadioButton(
                    selected = selectedPayment == method,
                    onClick = { onPaymentChange(method) }
                )
                TextButton(onClick = { onPaymentChange(method) }) {
                    Text(method)
                }
            }
        }

        if (showError) {
            Text(
                text = "Wybierz metodę płatności",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            if (selectedPayment.trim().isNotEmpty()) {
                showError = false
                onNext()
            } else {
                showError = true
            }
        }) {
            Text("Podsumowanie")
        }

        TextButton(onClick = onBack) {
            Text("Wstecz")
        }
    }
}


@Composable
fun SummaryScreen(
    name: String,
    street: String,
    postalCode: String,
    city: String,
    payment: String,
    onConfirm: () -> Unit,
    onBack: () -> Unit
) {
    val cartProducts = products.take(2)
    val totalPrice = cartProducts.sumOf { it.price }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Podsumowanie", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        cartProducts.forEach { product ->
            Text("${product.name} - ${product.price} zł")
        }

        Spacer(Modifier.height(16.dp))
        Text("Razem: $totalPrice zł")

        Spacer(Modifier.height(16.dp))

        Text("Adres dostawy:", style = MaterialTheme.typography.titleMedium)
        Text(name)
        Text(street)
        Text("$postalCode $city")

        Spacer(Modifier.height(16.dp))

        Text("Metoda płatności:", style = MaterialTheme.typography.titleMedium)
        Text(payment)

        Spacer(Modifier.height(16.dp))

        Button(onClick = onConfirm) {
            Text("Potwierdź zamówienie")
        }

        TextButton(onClick = onBack) {
            Text("Wstecz")
        }
    }
}
