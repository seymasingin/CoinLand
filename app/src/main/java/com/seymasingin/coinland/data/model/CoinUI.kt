package com.seymasingin.coinland.data.model

import com.google.gson.annotations.SerializedName

data class CoinUI(
    val id: String,
    val symbol: String,
    val name: String?,
    val image: String,

    @SerializedName("current_price")
    val price: Double?,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage: String,
)




