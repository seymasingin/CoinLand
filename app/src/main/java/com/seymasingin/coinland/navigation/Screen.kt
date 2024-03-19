package com.seymasingin.coinland.navigation

sealed class Screen(val route: String, val label: String) {
    data object Home : Screen("home", "Home")
    data object Stock: Screen("stock",  "Stock")
}