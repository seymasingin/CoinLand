package com.seymasingin.coinland.data.source.remote

import com.seymasingin.coinland.data.model.CoinDetail
import com.seymasingin.coinland.data.model.CoinUI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

interface CoinService {

    @GET ("coins/markets")
    suspend fun getCoinsMarkets(
        @Query("vs_currency") currency: String = "usd",
        @Query("page") page: Int = 1,
        @Query("per_page") numCoinsPerPage: Int = 100,
        @Query("order") order: String = "market_cap_desc",
        @Query("sparkline") includeSparkline7dData: Boolean = false,
    ): List<CoinUI>

    @GET ("coins/{id}?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false")
    suspend fun getCoinDetail(
        @Path("id") id: String,
    ) : CoinDetail
}