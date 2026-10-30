package com.example.lista1_composeshop

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lista1_composeshop.ui.theme.Lista1_ComposeShopTheme
import androidx.navigation.toRoute
import kotlinx.coroutines.launch
import androidx.navigation.compose.navigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Lista1_ComposeShopTheme {
                ShopApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var name by rememberSaveable { mutableStateOf("") }
    var street by rememberSaveable { mutableStateOf("") }
    var postalCode by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var payment by rememberSaveable { mutableStateOf("") }
    val favoriteIds = remember { mutableStateListOf<Int>() }
    val backStackEntry by navController.currentBackStackEntryAsState()
    val current = backStackEntry?.destination
    val inCheckout = current?.hierarchy?.any {
        it.hasRoute<Checkout>()
    } == true
    LaunchedEffect(inCheckout) {
        if (inCheckout) {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = !inCheckout,
        drawerContent = {
            ModalDrawerSheet {
                Text("ComposeShop", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)

                NavigationDrawerItem(
                    label = { Text("Profil")},
                    selected = current?.hasRoute<Profile>() == true,
                    onClick = {
                        navController.openTopLevel(Profile)
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Historia zamówień")},
                    selected = current?.hasRoute<OrderHistory>() == true,
                    onClick = {
                        navController.openTopLevel(OrderHistory)
                        scope.launch { drawerState.close() }
                    }
                )

                NavigationDrawerItem(
                    label = { Text("Ustawienia")},
                    selected = current?.hasRoute<Settings>() == true,
                    onClick = {
                        navController.openTopLevel(Settings)
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {Text("ComposeShop")},
                    navigationIcon = {
                        if (!inCheckout) {
                            TextButton(
                                onClick = {
                                    scope.launch { drawerState.open() }
                                }
                            ) {
                                Text("Menu")
                            }
                        }
                    }
                )
            },
            bottomBar = {
                if (!inCheckout) {
                    NavigationBar() {
                        NavigationBarItem(
                            selected = current?.hasRoute<Home>() == true,
                            onClick = {
                                navController.openTopLevel(Home)
                            },
                            icon = {},
                            label = { Text("Start") }
                        )

                        NavigationBarItem(
                            selected = current?.hasRoute<Categories>() == true,
                            onClick = {
                                navController.openTopLevel(Categories)
                            },
                            icon = {},
                            label = { Text("Kategorie") }
                        )

                        NavigationBarItem(
                            selected = current?.hasRoute<Cart>() == true,
                            onClick = {
                                navController.openTopLevel(Cart)
                            },
                            icon = {},
                            label = { Text("Koszyk") }
                        )

                        NavigationBarItem(
                            selected = current?.hasRoute<Favorites>() == true,
                            onClick = {
                                navController.openTopLevel(Favorites)
                            },
                            icon = {},
                            label = { Text("Ulubione") }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Home,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<Home> {
                    HomeScreen(
                        favoriteIds = favoriteIds,
                        onFavoriteClick = { id ->
                            if (id in favoriteIds) {
                                favoriteIds.remove(id)
                            } else {
                                favoriteIds.add(id)
                            }
                        },
                        onDetailsClick = { id ->
                            navController.navigate(Details(id))
                        }
                    )
                }
                composable<Categories> {
                    CategoriesScreen(
                        favoriteIds = favoriteIds,
                        onFavoriteClick = { id ->
                            if (id in favoriteIds) {
                                favoriteIds.remove(id)
                            } else {
                                favoriteIds.add(id)
                            }
                        },
                        onDetailsClick = { id ->
                            navController.navigate(Details(id))
                        }
                    )
                }
                composable<Cart> {
                    CartScreen(
                        onCheckoutClick = {
                            navController.navigate(Checkout)
                        }
                    )
                }
                composable<Favorites> {
                    FavoritesScreen(
                        favoriteIds = favoriteIds,
                        onFavoriteClick = { id ->
                            favoriteIds.remove(id)
                        },
                        onDetailsClick = { id ->
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
                composable<Profile> {
                    ProfileScreen()
                }
                composable<OrderHistory> {
                    OrderHistoryScreen()
                }
                composable<Settings> {
                    SettingsScreen()
                }


                navigation<Checkout>(
                    startDestination = Address
                ) {
                    composable<Address> {
                        AddressScreen(
                            name = name,
                            street = street,
                            postalCode = postalCode,
                            city = city,
                            onNameChange = { name = it },
                            onStreetChange = { street = it },
                            onPostalCodeChange = { postalCode = it },
                            onCityChange = { city = it },
                            onNext = {
                                navController.navigate(Payment)
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<Payment> {
                        PaymentScreen(
                            selectedPayment = payment,
                            onPaymentChange = { payment = it },
                            onNext = {
                                navController.navigate(Summary)
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<Summary> {
                        SummaryScreen(
                            name = name,
                            street = street,
                            postalCode = postalCode,
                            city = city,
                            payment = payment,
                            onConfirm = {
                                name = ""
                                street = ""
                                postalCode = ""
                                city = ""
                                payment = ""

                                Toast.makeText(
                                    context,
                                    "Zamówienie zostało przyjęte!",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.navigate(Home) {
                                    popUpTo<Checkout> {
                                        inclusive = true
                                    }
                                    launchSingleTop = true
                                }
                            },
                            onBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

fun <T: Any> NavController.openTopLevel(route: T) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}