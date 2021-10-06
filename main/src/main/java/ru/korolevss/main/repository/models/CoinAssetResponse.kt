package ru.korolevss.main.repository.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinAssetResponse(

    @SerialName("id")
    val id: String = "",

    @SerialName("rank")
    val rank: Int = 0,

    @SerialName("symbol")
    val symbol: String = "",

    @SerialName("name")
    val name: String = "",

    @SerialName("supply")
    val supply: Double? = null,

    @SerialName("maxSupply")
    val maxSupply: Double? = null,

    @SerialName("marketCapUsd")
    val marketCapUsd: Double? = null,

    @SerialName("volumeUsd24Hr")
    val volumeUsd24Hr: Double? = null,

    @SerialName("priceUsd")
    val priceUsd: Double? = null,

    @SerialName("changePercent24Hr")
    val changePercent24Hr: Double? = null,

    @SerialName("vwap24Hr")
    val vwap24Hr: Double? = null,
)
