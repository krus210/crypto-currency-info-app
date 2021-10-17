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
    val open: Double? = null,

    @SerialName("high")
    val high: Double? = null,

    @SerialName("low")
    val low: Double? = null,

    @SerialName("close")
    val close: Double? = null,

    @SerialName("volume")
    val volume: Double? = null,

    @SerialName("period")
    val period: Long? = null,

)