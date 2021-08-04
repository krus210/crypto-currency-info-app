package ru.korolevss.detail.repository.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinAssetResponse(

    @SerialName("id")
    val summary: String = "",

    @SerialName("rank")
    val rank: Int = 0,

    @SerialName("symbol")
    val symbol: String = "",

    @SerialName("name")
    val name: String = "",

    @SerialName("supply")
    val supply: Double = 0.0,

    @SerialName("maxSupply")
    val maxSupply: Double = 0.0,

    @SerialName("marketCapUsd")
    val marketCapUsd: Double = 0.0,

    @SerialName("volumeUsd24Hr")
    val volumeUsd24Hr: Double = 0.0,

    @SerialName("priceUsd")
    val priceUsd: Double = 0.0,

    @SerialName("changePercent24Hr")
    val changePercent24Hr: Double = 0.0,

    @SerialName("vwap24Hr")
    val vwap24Hr: Double = 0.0,
)
