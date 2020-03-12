package com.sothsez.coins.manager.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.sothsez.coins.MainApplication
import com.sothsez.coins.R
import com.sothsez.coins.view.model.CoinCollection
import kotlinx.android.synthetic.main.item_coin.view.*

class CoinAdapter(val actis: Activity, private val items: CoinCollection) :
    RecyclerView.Adapter<CoinAdapter.CoinHolder>() {

    val dao = items.data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinHolder {
        return CoinHolder(
            LayoutInflater.from(MainApplication.applicationContext()).inflate(
                R.layout.item_coin,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: CoinHolder, position: Int) {
        holder.setView(position)
    }

    inner class CoinHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setView(position: Int) {
            if (position % 5 == 4) {
                itemView.frame_regular.visibility = View.GONE
                itemView.frame_different.visibility = View.VISIBLE
                SvgLoader.pluck()
                    .with(actis)
                    .setPlaceHolder(R.drawable.ic, R.drawable.ic)
                    .load(dao.coins.get(position).iconUrl, itemView.image_coin_different)
                itemView.text_coin_name_different.setText(dao.coins.get(position)?.name)

            } else {
                itemView.frame_regular.visibility = View.VISIBLE
                itemView.frame_different.visibility = View.GONE
                SvgLoader.pluck()
                    .with(actis)
                    .setPlaceHolder(R.drawable.ic, R.drawable.ic)
                    .load(dao.coins.get(position).iconUrl, itemView.image_coin_regular)
                itemView.text_coin_name_regular.setText(dao.coins.get(position)?.name)
                itemView.text_coin_description_regular.setText(dao.coins.get(position)?.description)
            }
        }
    }
}