package com.seymasingin.coinland.data.source.remote

import com.seymasingin.coinland.data.model.CoinDetail
import com.seymasingin.coinland.data.model.CoinsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService {

    @GET ("/coins/list")
    suspend fun getCoins(): Response<CoinsList>

    @GET ("coins/{id}?localization=false&community_data=false&developer_data=false&sparkline=false")
    suspend fun getCoinDetail(): Response<CoinDetail>

    @GET("search")
    suspend fun getSearch(
        @Query("query") query: String
    ): Response<CoinsList>

}