package com.sothsez.coins.manager.http

import com.sothsez.coins.view.model.CoinCollection
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("coins/")
    fun getData() : Call<CoinCollection>
}