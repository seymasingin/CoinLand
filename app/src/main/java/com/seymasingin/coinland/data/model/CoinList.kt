package com.seymasingin.coinland.data.model

import androidx.compose.ui.graphics.Color

data class CoinUI(
    val id: String,
    val symbol: String,
    val name: String,
    val imageUrl: String,
    val trendColor: Color,
    val sparklineData: List<DataPoint>,
    val price: String,
)

data class CoinList(
    val coinList: List<CoinUI>
)

