package com.sothsez.coins.view.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CoinCollection(
    @SerializedName("status") val status: String?,
    @SerializedName("data") val data: CoinData
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable<CoinData>(CoinData::class.java.classLoader) as CoinData
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeParcelable(data, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoinCollection> {
        override fun createFromParcel(parcel: Parcel): CoinCollection {
            return CoinCollection(parcel)
        }

        override fun newArray(size: Int): Array<CoinCollection?> {
            return arrayOfNulls(size)
        }
    }
}

data class CoinData(
    @SerializedName("stats") val stats: CoinStatus,
    @SerializedName("base") val base: CoinBase,
    @SerializedName("coins") val coins: ArrayList<Coins>
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable<CoinStatus>(CoinStatus::class.java.classLoader) as CoinStatus,
        parcel.readParcelable<CoinBase>(CoinBase::class.java.classLoader) as CoinBase,
        parcel.readArrayList(Coins::class.java.classLoader) as ArrayList<Coins>
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(stats, flags)
        parcel.writeParcelable(base, flags)
        parcel.writeList(coins as List<*>?)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoinData> {
        override fun createFromParcel(parcel: Parcel): CoinData {
            return CoinData(parcel)
        }

        override fun newArray(size: Int): Array<CoinData?> {
            return arrayOfNulls(size)
        }
    }
}

data class CoinStatus(
    @SerializedName("total") val total: Long,
    @SerializedName("offset") val offset: Long,
    @SerializedName("limit") val limit: Long,
    @SerializedName("order") val order: String?,
    @SerializedName("base") val base: String?,
    @SerializedName("totalMarkets") val totalMarkets: Long,
    @SerializedName("totalExchanges") val totalExchanges: Long,
    @SerializedName("totalMarketCap") val totalMarketCap: Float,
    @SerializedName("total24hVolume") val total24hVolume: Float
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readFloat(),
        parcel.readFloat()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(total)
        parcel.writeLong(offset)
        parcel.writeLong(limit)
        parcel.writeString(order)
        parcel.writeString(base)
        parcel.writeLong(totalMarkets)
        parcel.writeLong(totalExchanges)
        parcel.writeFloat(totalMarketCap)
        parcel.writeFloat(total24hVolume)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoinStatus> {
        override fun createFromParcel(parcel: Parcel): CoinStatus {
            return CoinStatus(parcel)
        }

        override fun newArray(size: Int): Array<CoinStatus?> {
            return arrayOfNulls(size)
        }
    }
}

data class CoinBase(
    @SerializedName("symbol") val symbol: String?,
    @SerializedName("sign") val sign: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(symbol)
        parcel.writeString(sign)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoinBase> {
        override fun createFromParcel(parcel: Parcel): CoinBase {
            return CoinBase(parcel)
        }

        override fun newArray(size: Int): Array<CoinBase?> {
            return arrayOfNulls(size)
        }
    }
}

data class Coins(
    @SerializedName("id") val id: Long,
    @SerializedName("uuid") val uuid: String?,
    @SerializedName("slug") val slug: String?,
    @SerializedName("symbol") val symbol: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("color") val color: String?,
    @SerializedName("iconType") val iconType: String?,
    @SerializedName("iconUrl") val iconUrl: String?,
    @SerializedName("websiteUrl") val websiteUrl: String?,
    @SerializedName("socials") val socials: ArrayList<Socials>,
    @SerializedName("links") val links: ArrayList<Links>,
    @SerializedName("confirmedSupply") val confirmedSupply: Boolean,
    @SerializedName("numberOfMarkets") val numberOfMarkets: Long,
    @SerializedName("numberofExchanges") val numberofExchanges: Long,
    @SerializedName("type") val type: String?,
    @SerializedName("volume") val volume: Long,
    @SerializedName("marketCap") val marketCap: Long,
    @SerializedName("price") val price: Float,
    @SerializedName("circulatingSupply") val circulatingSupply: Float,
    @SerializedName("totalSupply") val totalSupply: Float,
    @SerializedName("approvedSupply") val approvedSupply: Boolean,
    @SerializedName("firstSeen") val firstSeen: Long,
    @SerializedName("change") val change: Float,
    @SerializedName("rank") val rank: Long,
    @SerializedName("history") val history: ArrayList<String>,
    @SerializedName("allTimeHigh") val allTimeHigh: AllTimeHigh,
    @SerializedName("penalty") val penalty: Boolean
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readArrayList(Socials::class.java.classLoader) as ArrayList<Socials>,
        parcel.readArrayList(Links::class.java.classLoader) as ArrayList<Links>,
        parcel.readByte() != 0.toByte(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readByte() != 0.toByte(),
        parcel.readLong(),
        parcel.readFloat(),
        parcel.readLong(),
        parcel.readArrayList(String::class.java.classLoader) as ArrayList<String>,
        parcel.readParcelable<AllTimeHigh>(AllTimeHigh::class.java.classLoader) as AllTimeHigh,
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(uuid)
        parcel.writeString(slug)
        parcel.writeString(symbol)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(color)
        parcel.writeString(iconType)
        parcel.writeString(iconUrl)
        parcel.writeString(websiteUrl)
        parcel.writeList(socials as List<*>?)
        parcel.writeList(links as List<*>?)
        parcel.writeByte(if (confirmedSupply) 1 else 0)
        parcel.writeLong(numberOfMarkets)
        parcel.writeLong(numberofExchanges)
        parcel.writeString(type)
        parcel.writeLong(volume)
        parcel.writeLong(marketCap)
        parcel.writeFloat(price)
        parcel.writeFloat(circulatingSupply)
        parcel.writeFloat(totalSupply)
        parcel.writeByte(if (approvedSupply) 1 else 0)
        parcel.writeLong(firstSeen)
        parcel.writeFloat(change)
        parcel.writeLong(rank)
        parcel.writeList(history as List<*>?)
        parcel.writeParcelable(allTimeHigh, flags)
        parcel.writeByte(if (penalty) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coins> {
        override fun createFromParcel(parcel: Parcel): Coins {
            return Coins(parcel)
        }

        override fun newArray(size: Int): Array<Coins?> {
            return arrayOfNulls(size)
        }
    }
}

data class Socials(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("type") val type: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Socials> {
        override fun createFromParcel(parcel: Parcel): Socials {
            return Socials(parcel)
        }

        override fun newArray(size: Int): Array<Socials?> {
            return arrayOfNulls(size)
        }
    }
}

data class Links(
    @SerializedName("name") val name: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("url") val url: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Links> {
        override fun createFromParcel(parcel: Parcel): Links {
            return Links(parcel)
        }

        override fun newArray(size: Int): Array<Links?> {
            return arrayOfNulls(size)
        }
    }
}

data class AllTimeHigh(
    @SerializedName("price") val price: Float,
    @SerializedName("timestamp") val timestamp: Long
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readFloat(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeFloat(price)
        parcel.writeLong(timestamp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AllTimeHigh> {
        override fun createFromParcel(parcel: Parcel): AllTimeHigh {
            return AllTimeHigh(parcel)
        }

        override fun newArray(size: Int): Array<AllTimeHigh?> {
            return arrayOfNulls(size)
        }
    }
}