package com.seymasingin.coinland.data.source.remote

import com.seymasingin.coinland.data.model.CoinDetail
import com.seymasingin.coinland.data.model.CoinList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService {

    @GET ("coins/markets")
    suspend fun getCoinsMarkets(
        @Query("vs_currency") currency: String = "usd",
        @Query("page") page: Int = 1,
        @Query("per_page") numCoinsPerPage: Int = 100,
        @Query("sparkline") includeSparkline7dData: Boolean = false,
    ): Response<CoinList>

    /*@GET ("coins/{id}?localization=false&community_data=false&developer_data=false&sparkline=false")
    suspend fun getCoinDetail(): Response<CoinDetail>

    @GET("search")
    suspend fun getSearch(
        @Query("query") query: String
    ): Response<CoinList>*/
}