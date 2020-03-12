package com.sothsez.coins.manager

import android.content.Context
import com.sothsez.coins.MainApplication
import com.sothsez.coins.view.model.CoinCollection

class CoinDao(dao: CoinCollection) {

    private val cons: Context

    init {
        cons = MainApplication.applicationContext()
    }

    var dao: CoinCollection
        get() {
            return dao
        }
        set(value) {
            dao = value
        }



}