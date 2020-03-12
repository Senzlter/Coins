package com.sothsez.coins.activity

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sothsez.coins.R
import com.sothsez.coins.manager.HttpManager
import com.sothsez.coins.manager.adapter.CoinAdapter
import com.sothsez.coins.view.model.CoinCollection
import com.sothsez.coins.view.model.Coins
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private lateinit var globalDao: CoinCollection

    private var firstPosition: Int = 0;
    private var listSize: Int = 10
    private var loaded = false
    private var lastSearch: String = ""
    private var coinsList: ArrayList<Coins> = arrayListOf()
    private val linearLayoutManager = LinearLayoutManager(this)

    private val lastVisibleItemPosition: Int
        get() = linearLayoutManager.findLastVisibleItemPosition()
    private val firstVisibleItemPosition: Int
        get() = linearLayoutManager.findFirstVisibleItemPosition()

    companion object {
        private val TYPE_SYMBOL = 0
        private val TYPE_PREFIX = 1
        private val TYPE_SLUG = 2
        private val TYPE_ID = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listSize = 10

        recycler_coin_list.layoutManager = linearLayoutManager

        if (savedInstanceState != null) {
            onRestoreInstanceStateManual(savedInstanceState)
            if (lastSearch != "") searchCoins(lastSearch!!) else coinsList = globalDao.data.coins
            reloadData(firstPosition)
        } else loadApiData()

        edit_search.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.d("ddd", "search enter")
                lastSearch = edit_search.text.toString()
                edit_search.hint = lastSearch
                edit_search.text.clear()
                searchCoins(lastSearch!!)

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(v.windowToken, 0)
                true
            }
            false
        }

        swipe_coin_list.setOnRefreshListener {
            Handler().postDelayed({
                listSize = 10
                coinsList = globalDao.data.coins
                reloadData(0)
                swipe_coin_list.isRefreshing = false
                edit_search.hint = "Search ( Prefix, Symbols, Slugs or IDs )"
                lastSearch = ""

            }, 3000)
        }

        swipe_coin_list.setColorSchemeColors(
            Color.parseColor("#008744"),
            Color.parseColor("#0057e7"),
            Color.parseColor("#d62d20")
        )

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (globalDao != null) {
            outState.putParcelable("dao", globalDao)
            outState.putString("search", lastSearch)
            outState.putInt("size", listSize)
            outState.putInt("position", firstVisibleItemPosition)
        }

    }

    private fun onRestoreInstanceStateManual(savedInstanceState: Bundle) {
        globalDao = savedInstanceState.getParcelable("dao")!!
        lastSearch = savedInstanceState.getString("search")!!
        listSize = savedInstanceState.getInt("size")
        firstPosition = savedInstanceState.getInt("position")
    }

    private fun searchCoins(str: String) {
        val temp = str.split("")
        coinsList = arrayListOf()
        when (separateSearchMode(str)) {
            TYPE_PREFIX -> {
                for (temp in globalDao.data.coins.filter { it.name!!.contains(str) }) {
                    coinsList.add(temp)
                }
            }
            TYPE_SYMBOL -> {
                for (temp in globalDao.data.coins.filter { it.symbol!!.contains(str) }) {
                    coinsList.add(temp)
                }
            }
            TYPE_SLUG -> {
                for (temp in globalDao.data.coins.filter { it.slug!!.contains(str) }) {
                    coinsList.add(temp)
                }
            }
            TYPE_ID -> {
                for (temp in globalDao.data.coins.filter { it.id == str.toLongOrNull() }) {
                    coinsList.add(temp)
                }
            }
            else -> throw IllegalArgumentException("Invalid type")
        }
        reloadData(0)
    }

    private fun separateSearchMode(str: String): Int {
        return when {
            str.filter { it.isUpperCase() }.length == str.length -> TYPE_SYMBOL
            str.contains("-") -> TYPE_SLUG
            str.toIntOrNull() != null -> TYPE_ID
            else -> TYPE_PREFIX
        }
    }

    private fun getRecyclerScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView!!.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    recycler_coin_list.removeOnScrollListener(scrollListener)
                    if (listSize < globalDao.data.stats.limit && !loaded) {
                        loadMore()
                        loaded = true
                    }
                }
            }
        }

        recycler_coin_list.addOnScrollListener(scrollListener)

    }

    private fun loadApiData() {
        val call = HttpManager().getService().getData()
        call.enqueue(CoinsResponse())
    }

    private fun reloadData(lastPosition: Int) {
        recycler_coin_list.adapter =
            CoinAdapter(
                this@MainActivity,
                coinsList,
                listSize
            )
        recycler_coin_list.scrollToPosition(lastPosition)
        getRecyclerScrollListener()
        loaded = false
    }

    fun loadMore() {
        Handler().postDelayed({
            listSize += 10
            reloadData(firstVisibleItemPosition)
        }, 2000)
    }

    inner class CoinsResponse() : Callback<CoinCollection> {
        override fun onFailure(call: Call<CoinCollection>, t: Throwable) {
            Toast.makeText(
                applicationContext,
                "not success in response" + t.message,
                Toast.LENGTH_SHORT
            ).show()
            Log.d("ddd", t.message)
        }

        override fun onResponse(call: Call<CoinCollection>, response: Response<CoinCollection>) {
            swipe_coin_list.isRefreshing = false

            if (response.isSuccessful) {
                val dao = response.body()
                Log.d("ddd", dao?.status.toString())

                recycler_coin_list.adapter =
                    CoinAdapter(
                        this@MainActivity,
                        dao!!.data.coins,
                        listSize
                    )
                globalDao = dao!!
                coinsList = globalDao.data.coins
                getRecyclerScrollListener()
            }
        }
    }
}
