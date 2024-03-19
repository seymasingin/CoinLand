package com.seymasingin.coinland.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.seymasingin.coinland.R

@Composable
fun BottomNavigationBar(navController: NavController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        val items = listOf(Screen.Home, Screen.Stock)

        items.forEachIndexed { _, item ->

            NavigationBarItem(
                icon = {
                    when (item) {
                        Screen.Home -> Icon(painter =painterResource(id = R.drawable.ic_home), contentDescription = "")
                        Screen.Stock -> Icon(painter = painterResource(id = R.drawable.ic_stock), contentDescription = "")
                    }
                       },
                label = { Text(item.label) },
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}




