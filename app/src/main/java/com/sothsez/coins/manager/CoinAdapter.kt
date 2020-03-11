package com.sothsez.coins.manager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.sothsez.coins.MainApplication
import com.sothsez.coins.R
import com.sothsez.coins.view.model.CoinData
import kotlinx.android.synthetic.main.item_coin.view.*

class CoinAdapter(val cons: Context, private val items: CoinData): RecyclerView.Adapter<CoinAdapter.CoinHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinHolder {
        return CoinHolder( LayoutInflater.from(cons).inflate(R.layout.item_coin , parent, false))
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: CoinHolder, position: Int) {
        holder.setView(position)
    }

    inner class CoinHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setView(position: Int){
            if (position % 5 == 4){
                itemView.frame_regular.visibility = View.GONE
                itemView.frame_different.visibility = View.VISIBLE
            }
        }
    }
}