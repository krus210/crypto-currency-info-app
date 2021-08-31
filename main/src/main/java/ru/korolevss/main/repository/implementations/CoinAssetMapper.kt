package ru.korolevss.main.repository.implementations

import ru.korolevss.core_api.dto.CoinAsset
import ru.korolevss.main.repository.models.CoinAssetResponse

fun CoinAssetResponse.mapToCoinAsset() : CoinAsset = CoinAsset(
    this.id,
    this.rank,
    this.symbol,
    this.name,
    this.supply,
    this.maxSupply,
    this.marketCapUsd,
    this.volumeUsd24Hr,
    this.priceUsd,
    this.changePercent24Hr,
    this.vwap24Hr
)