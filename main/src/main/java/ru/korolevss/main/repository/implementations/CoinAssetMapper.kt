package ru.korolevss.main.repository.implementations

import ru.korolevss.core_api.dto.CoinAsset
import ru.korolevss.main.repository.models.CoinAssetResponse

fun CoinAssetResponse.mapToCoinAsset() : CoinAsset = CoinAsset(
    this.id,
    this.rank,
    this.symbol,
    this.name,
    this.supply ?: 0.0,
    this.maxSupply ?: 0.0,
    this.marketCapUsd ?: 0.0,
    this.volumeUsd24Hr ?: 0.0,
    this.priceUsd ?: 0.0,
    this.changePercent24Hr ?: 0.0,
    this.vwap24Hr ?: 0.0
)