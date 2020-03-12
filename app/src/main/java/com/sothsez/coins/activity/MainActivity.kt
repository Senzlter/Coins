package com.sothsez.coins.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sothsez.coins.R
import com.sothsez.coins.manager.CoinDao
import com.sothsez.coins.manager.HttpManager
import com.sothsez.coins.manager.adapter.CoinAdapter
import com.sothsez.coins.view.model.CoinCollection
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        loadApiData()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("ddd", "here main")

        recycler_coin_list.layoutManager = LinearLayoutManager(applicationContext)



    }

    fun loadApiData(){
        val call = HttpManager().getService().getData()

        call.enqueue(CoinsResponse())

    }

    inner class CoinsResponse(): Callback<CoinCollection> {

        override fun onFailure(call: Call<CoinCollection>, t: Throwable) {
            Toast.makeText(applicationContext, "not success in response" + t.message, Toast.LENGTH_SHORT).show()
            Log.d("ddd", t.message)

        }

        override fun onResponse(call: Call<CoinCollection>, response: Response<CoinCollection>) {

            if (response.isSuccessful){

                val dao = response.body()
                Log.d("ddd", dao?.status.toString())
                Log.d("ddd", dao?.data?.coins?.get(0)?.iconUrl.toString())

                recycler_coin_list.adapter =
                    CoinAdapter(
                        this@MainActivity,
                        dao!!
                    )

                for (isit in dao.data.coins){
                    Log.d("ddd", isit.iconUrl)
                    Log.d("ddd", isit.iconType)
                }

            }
        }
    }
}
