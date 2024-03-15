package com.seymasingin.coinland.data.model

import android.media.Image
import com.google.gson.annotations.SerializedName

data class CoinDetail (


    @SerializedName("community_score")
    val communityScore: Double,

    @SerializedName("country_origin")
    val countryOrigin: String,

    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String,

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

    @SerializedName("liquidity_score")
    val liquidityScore: Double,

    @SerializedName("market_cap_rank")
    val marketCapRank: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("symbol")
    val symbol: String,

    @SerializedName("current_price")
    val price: Double,

    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage: String,

    val sparklineData: List<DataPoint>,
)