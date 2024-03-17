package com.seymasingin.coinland.data.model

import com.google.gson.annotations.SerializedName

data class CoinDetail (

    @SerializedName("id")
    val id: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("last_updated")
    val lastUpdated: String,

    @SerializedName("high_24h")
    val high24h: String,

    @SerializedName("low_24h")
    val low24h: String,

    @SerializedName("ath")
    val ath: String,

    @SerializedName("atl")
    val atl: String,

    @SerializedName("market_cap")
    val marketCap: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("current_price")
    val price: Double,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage: String,
)