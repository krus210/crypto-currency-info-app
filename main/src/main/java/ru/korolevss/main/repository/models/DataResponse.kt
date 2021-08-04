package ru.korolevss.main.repository.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataResponse(

    @SerialName("data")
    val data: List<CoinAssetResponse> = emptyList(),

    @SerialName("timestamp")
    val timestamp: Long = 0L,
)