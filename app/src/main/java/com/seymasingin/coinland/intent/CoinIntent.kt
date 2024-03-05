package com.seymasingin.coinland.intent

import com.seymasingin.coinland.data.model.CoinUI

sealed class CoinsListUiState {
    data object Idle : CoinsListUiState()
    data class Refreshing(val isAutomaticRefresh: Boolean) : CoinsListUiState()
    data class Error(val message: String) : CoinsListUiState()
    data class Success(val coins: List<CoinUI>) : CoinsListUiState()
}