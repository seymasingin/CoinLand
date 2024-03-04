package com.seymasingin.coinland.data.model

import androidx.compose.ui.graphics.Color

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val imageUrl: String,
    val price: String,
    val marketCapRank: String,
    val priceChangePercentage: String,
    val trendColor: Color,
    val lastUpdate: String
)


