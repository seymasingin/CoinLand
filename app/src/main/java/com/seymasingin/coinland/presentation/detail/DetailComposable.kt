package com.seymasingin.coinland.presentation.detail

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.detailComposable(
    detailViewModel: DetailViewModel

) {
    composable(
        route = "detail/{coinId}",
        arguments = listOf(navArgument("coinId"){
            type = NavType.StringType
        })
    ){ navbackStackEntry ->
        val coinId = navbackStackEntry.arguments!!.getString("coinId")

        LaunchedEffect(coinId){
            if (coinId != null) {
                detailViewModel.getSelectedCoin(coinId)
            }
        }

        val selectedCoin by detailViewModel.selectedCoin.collectAsState()

        Detail( detailViewModel, selectedCoin)

    }
}