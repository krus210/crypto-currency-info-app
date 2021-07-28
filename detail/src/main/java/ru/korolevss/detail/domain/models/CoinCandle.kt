package ru.korolevss.detail.domain.models

import kotlinx.serialization.SerialName

data class CoinCandle(
    val open: Double = 0.0,
    val high: Double = 0.0,
    val low: Double = 0.0,
    val close: Double = 0.0,
    val volume: Double = 0.0,
    val period: Long = 0L
)
