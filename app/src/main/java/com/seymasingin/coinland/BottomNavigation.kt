package com.seymasingin.coinland

import androidx.annotation.StringRes

sealed class BottomNavigation(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("profile", R.string.profile)
}