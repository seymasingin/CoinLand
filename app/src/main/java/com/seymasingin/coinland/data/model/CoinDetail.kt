package com.seymasingin.coinland.data.model

import android.media.Image
import com.google.gson.annotations.SerializedName

data class CoinDetail (

    @SerializedName("block_time_in_minutes")
    val blockTimeInMinutes: Int,

    @SerializedName("categories")
    val categories: List<String>,

    @SerializedName("community_score")
    val communityScore: Double,

    @SerializedName("country_origin")
    val countryOrigin: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("genesis_date")
    val genesisDate: String,

    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("image")
    val image: Image,

    @SerializedName("last_updated")
    val lastUpdated: String,

    @SerializedName("links")
    val links: String,

    @SerializedName("liquidity_score")
    val liquidityScore: Double,

    @SerializedName("market_cap_rank")
    val marketCapRank: Int,

    @SerializedName("name")
    val name: String?,

    @SerializedName("symbol")
    val symbol: String
)