package com.example.lista1_composeshop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Profil", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("Imię: Wiktoria")
        Text("Nazwisko: Domańska")
        Text("Email: wikt.domanska@gmail.com")
    }
}

@Composable
fun OrderHistoryScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Historia zamówień", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("12.10.2026 - Zrealizowano - 149 zł")
        Text("30.10.2026 - Wysłano - 89 zł")
        Text("01.09.2026 - W trakcie - 199 zł")
    }
}

@Composable
fun SettingsScreen() {
    var notifications by rememberSaveable {
        mutableStateOf(true)
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Ustawienia", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Row {
            Text("Powiadomienia")
            Spacer(Modifier.width(16.dp))
            Switch(
                checked = notifications,
                onCheckedChange = { notifications = it}
            )
        }
    }
}