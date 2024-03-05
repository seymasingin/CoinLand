package com.seymasingin.coinland.presentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.seymasingin.coinland.intent.CoinsListUiState

@Composable
fun Home(  viewModel: HomeViewModel) {

    val uiState by viewModel.uiState.collectAsState()

    Scaffold() { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding
        ) {
            when (val state = uiState) {
                is CoinsListUiState.Success -> {
                    items(state.coins) { coin ->
                        CoinItem(coin)
                    }
                }
                else -> {}
            }
        }
    }
}