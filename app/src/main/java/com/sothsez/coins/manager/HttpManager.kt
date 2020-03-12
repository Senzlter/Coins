package com.sothsez.coins.manager

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sothsez.coins.manager.http.ApiService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpManager {
    private val apiService: ApiService

    init {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coinranking.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getService() = apiService

}