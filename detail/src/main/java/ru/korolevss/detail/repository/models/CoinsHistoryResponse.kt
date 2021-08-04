package ru.korolevss.detail.repository.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinsHistoryResponse(

    @SerialName("data")
    val data: List<PriceHistoryResponse> = emptyList(),

    @SerialName("timestamp")
    val timestamp: Long = 0L
)

@Serializable
data class PriceHistoryResponse(

    @SerialName("priceUsd")
    val priceUsd: Double = 0.0,

    @SerialName("time")
    val time: Long = 0L
)
