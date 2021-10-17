package ru.korolevss.detail.repository.implementations

import ru.korolevss.detail.domain.models.CoinCandle
import ru.korolevss.detail.repository.models.CoinCandleResponse

fun CoinCandleResponse.toCoinCandle(): CoinCandle =
    CoinCandle(
        open = this.open ?: 0.0,
        high = this.high ?: 0.0,
        low = this.low ?: 0.0,
        close = this.close ?: 0.0,
        volume = this.volume ?: 0.0,
        period = this.period ?: 0
    )