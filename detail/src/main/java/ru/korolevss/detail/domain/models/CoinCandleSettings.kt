package ru.korolevss.detail.domain.models

data class CoinCandleSettings(
    val exchangeId: String,
    val interval: CandleInterval,
    val baseId: String,
    val quoteId: String,
)
