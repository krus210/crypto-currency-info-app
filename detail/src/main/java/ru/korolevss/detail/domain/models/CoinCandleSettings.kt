package ru.korolevss.detail.domain.models

data class CoinCandleSettings(
    val exchangeId: String = "poloniex",
    val interval: CandleInterval = CandleInterval.HOUR_EIGHT,
    val baseId: String,
    val quoteId: String = "tether",
)
