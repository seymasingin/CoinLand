package com.seymasingin.coinland.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    object Home : Screen("home", Icons.Default.Home,"Home")
    object Stock: Screen("stock", Icons.Filled.ShoppingCart, "Stock")
    object Profile: Screen("profile", Icons.Filled.Person,"Profile")
}