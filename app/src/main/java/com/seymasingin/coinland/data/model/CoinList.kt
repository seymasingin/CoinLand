package com.seymasingin.coinland.data.model

data class CoinsList(
    val id: String,
    val symbol: String,
    val name: String,
)

sealed interface CoinsListUiState {
    object Idle : CoinsListUiState
    data class Refreshing(val isAutomaticRefresh: Boolean) : CoinsListUiState
    data class Error(val message: String) : CoinsListUiState
}