package ru.korolevss.detail.repository.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataCoinResponse(
    @SerialName("data")
    val data: CoinAssetResponse = CoinAssetResponse(),

    @SerialName("timestamp")
    val timestamp: Long = 0L,
)