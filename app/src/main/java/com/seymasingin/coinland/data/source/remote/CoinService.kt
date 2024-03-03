package com.seymasingin.coinland.data.source.remote

import com.seymasingin.coinland.data.model.response.Coin
import retrofit2.Response
import retrofit2.http.GET

interface CoinService {

    @GET
    suspend fun getCoins(): Response<Coin>

}