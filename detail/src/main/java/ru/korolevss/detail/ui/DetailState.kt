package ru.korolevss.detail.ui

import ru.korolevss.detail.domain.models.CandleInterval
import ru.korolevss.detail.domain.models.CoinCandle

data class DetailState(
    val title: String,
    val coinCandles : List<CoinCandle> = emptyList(),
    val interval: CandleInterval = CandleInterval.DAY_ONE,
    val isLoading : Boolean = false,
    val hasServererror : Boolean = false,
    val hasNoInternetError : Boolean = false
)
