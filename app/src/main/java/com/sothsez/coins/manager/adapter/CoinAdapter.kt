package com.sothsez.coins.manager.adapter

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.sothsez.coins.MainApplication
import com.sothsez.coins.R
import com.sothsez.coins.view.model.CoinCollection
import com.sothsez.coins.view.model.Coins
import kotlinx.android.synthetic.main.item_coin.view.*
import kotlinx.android.synthetic.main.item_load.view.*

class CoinAdapter(
    val actis: Activity,
    private val dao: ArrayList<Coins>,
    val listSize: Int
) :RecyclerView.Adapter<CoinAdapter.CoinViewHolder<*>>() {

    companion object {
        private const val TYPE_LOAD = 0
        private const val TYPE_REGULAR = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder<*> {
        return when (viewType) {
            TYPE_REGULAR -> {
                CoinHolder(
                    LayoutInflater.from(MainApplication.applicationContext()).inflate(
                        R.layout.item_coin,
                        parent,
                        false
                    )
                )
            }
            TYPE_LOAD ->{
                CoinLoad(
                    LayoutInflater.from(MainApplication.applicationContext()).inflate(
                        R.layout.item_load,
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return if (dao.size > listSize) listSize + 1 else dao.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder<*>, position: Int) {
        when(holder){
            is CoinHolder -> {
                holder.setView(position)
            }
            is CoinLoad -> {
                holder.loading()
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when{
            position == listSize  -> TYPE_LOAD
            position < listSize -> TYPE_REGULAR
            else -> throw IllegalArgumentException("Invalid type of data " + position)
        }
    }

    inner class CoinLoad(itemView: View) : CoinViewHolder<CoinView>(itemView) {
        override fun bind(item: CoinView) {
        }

        fun loading(){
            itemView.progress_load
        }

    }

    inner class CoinHolder(itemView: View) : CoinViewHolder<CoinView>(itemView) {
        fun setView(position: Int) {
            if (position % 5 == 4) {
                itemView.frame_regular.visibility = View.GONE
                itemView.frame_different.visibility = View.VISIBLE

                GlideToVectorYou.justLoadImage(actis, Uri.parse(dao.get(position).iconUrl), itemView.image_coin_different)
                itemView.text_coin_name_different.setText(dao.get(position)?.name)

            } else {
                itemView.frame_regular.visibility = View.VISIBLE
                itemView.frame_different.visibility = View.GONE

                GlideToVectorYou.justLoadImage(actis, Uri.parse(dao.get(position).iconUrl), itemView.image_coin_regular)
                itemView.text_coin_name_regular.setText(dao.get(position)?.name)
                itemView.text_coin_description_regular.setText(dao.get(position)?.description)
            }
        }

        override fun bind(item: CoinView) {

        }
    }

    inner class CoinView {}

    abstract class CoinViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

}