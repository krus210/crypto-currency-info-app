package ru.korolevss.detail.repository.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataCoinMarketsResponse(

    @SerialName("data")
    val data: List<CoinMarketResponse> = emptyList(),

    @SerialName("timestamp")
    val timestamp: Long = 0L,
)

@Serializable
data class CoinMarketResponse(

    @SerialName("exchangeId")
    val exchangeId: String = "",

    @SerialName("baseId")
    val baseId: String = "",

    @SerialName("quoteId")
    val quoteId: String = "",

    @SerialName("baseSymbol")
    val baseSymbol: String = "",

    @SerialName("quoteSymbol")
    val quoteSymbol: String = "",

    @SerialName("volumeUsd24Hr")
    val volumeUsd24Hr: Double = 0.0,

    @SerialName("priceUsd")
    val priceUsd: Double = 0.0,

    @SerialName("volumePercent")
    val volumePercent: Double = 0.0

    )
