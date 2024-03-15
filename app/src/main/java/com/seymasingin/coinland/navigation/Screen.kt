package com.seymasingin.coinland.navigation

import com.seymasingin.coinland.data.model.CoinDetail
import com.seymasingin.coinland.data.model.CoinUI

sealed class Screen(val route: String, val label: String) {
   object Home : Screen("home", "Home"){
        object Detail: Screen("detail", "Detail")
    }
    object Stock: Screen("stock",  "Stock")
    object Profile: Screen("profile", "Profile")
}