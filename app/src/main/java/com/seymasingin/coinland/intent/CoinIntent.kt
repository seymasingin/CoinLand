package com.seymasingin.coinland.intent

sealed class CoinListIntent {
    object Refresh : CoinListIntent()
}