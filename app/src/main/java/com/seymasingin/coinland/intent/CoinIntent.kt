package com.seymasingin.coinland.intent

sealed class CoinIntent {
    data class TextChanged(val text: String) : CoinIntent()
    data object SendClicked : CoinIntent()
    data object MessageShown : CoinIntent()
}