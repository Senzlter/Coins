package com.sothsez.coins.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sothsez.coins.R
import com.sothsez.coins.manager.CoinAdapter
import com.sothsez.coins.view.model.CoinData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_coin_list.layoutManager = LinearLayoutManager(applicationContext)
        recycler_coin_list.adapter = CoinAdapter(applicationContext, CoinData())
    }
}
