package ru.korolevss.detail.domain.models

import kotlinx.serialization.SerialName

data class CoinMarket(
    val exchangeId: String = "",
    val baseId: String = "",
    val quoteId: String = "",
    val baseSymbol: String = "",
    val quoteSymbol: String = "",
    val volumeUsd24Hr: Double = 0.0,
    val priceUsd: Double = 0.0,
    val volumePercent: Double = 0.0
)
