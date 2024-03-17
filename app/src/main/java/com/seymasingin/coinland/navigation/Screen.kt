package com.seymasingin.coinland.navigation

sealed class Screen(val route: String, val label: String) {
    object Home : Screen("home", "Home")
    object Stock: Screen("stock",  "Stock")
    object Profile: Screen("profile", "Profile")
}