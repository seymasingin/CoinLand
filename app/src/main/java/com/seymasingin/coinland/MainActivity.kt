package com.seymasingin.coinland

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.seymasingin.coinland.navigation.BottomNavigationBar
import com.seymasingin.coinland.navigation.Screen
import com.seymasingin.coinland.presentation.home.Home
import com.seymasingin.coinland.presentation.Profile
import com.seymasingin.coinland.presentation.Stock
import com.seymasingin.coinland.presentation.detail.DetailViewModel
import com.seymasingin.coinland.presentation.detail.detailComposable
import com.seymasingin.coinland.presentation.home.HomeViewModel
import com.seymasingin.coinland.ui.theme.CoinLandTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

private lateinit var navController: NavHostController

private val homeViewModel: HomeViewModel by viewModels()
private val detailViewModel: DetailViewModel by viewModels()

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        CoinLandTheme {
            navController = rememberNavController()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomNavigationBar(navController)
                }
            ){innerPadding ->
                NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)){
                    composable(Screen.Home.route){
                        Home( viewModel = homeViewModel,
                            navController)
                    }
                    composable(Screen.Stock.route){ Stock(navController)}
                    composable(Screen.Profile.route){ Profile(navController)}
                    detailComposable(detailViewModel, navController)
                }
                    }
                }
            }
        }
    }


