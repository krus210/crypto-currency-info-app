package ru.korolevss.detail.repository.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataCoinCandlesResponse(

    @SerialName("data")
    val data: List<CoinCandleResponse> = emptyList(),

    @SerialName("timestamp")
    val timestamp: Long = 0L,
)


@Serializable
data class CoinCandleResponse(

    @SerialName("open")
    val open: Double = 0.0,

    @SerialName("high")
    val high: Double = 0.0,

    @SerialName("low")
    val low: Double = 0.0,

    @SerialName("close")
    val close: Double = 0.0,

    @SerialName("volume")
    val volume: Double = 0.0,

    @SerialName("period")
    val period: Long = 0,

)