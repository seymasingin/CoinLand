package com.seymasingin.coinland.navigation

import com.seymasingin.coinland.data.model.CoinDetail

sealed class Screen(val route: String, val label: String) {
    data object Home : Screen("home", "Home"){
        data class Detail(val coinId: String) : Screen("detail/$coinId", "Detail")
    }
    object Stock: Screen("stock",  "Stock")
    object Profile: Screen("profile", "Profile")

}