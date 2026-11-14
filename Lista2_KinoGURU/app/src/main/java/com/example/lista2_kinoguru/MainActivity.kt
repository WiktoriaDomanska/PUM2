package com.example.lista2_kinoguru

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.lista2_kinoguru.ui.theme.Lista2_KinoGURUTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lista2_KinoGURUTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    KinoApp()
                }
            }
        }
    }
}

@Composable
fun KinoApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                onMovieClick = { id ->
                    navController.navigate(Details(id))
                }
            )
        }

        composable<Details> { entry ->
            val route = entry.toRoute<Details>()

            DetailsScreen(
                id = route.id,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}