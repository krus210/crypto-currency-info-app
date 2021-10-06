package ru.korolevss.core_api.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_assets")
data class CoinAsset(
    @PrimaryKey
    val id: String = "",
    val rank: Int = 0,
    val symbol: String = "",
    val name: String = "",
    val supply: Double = 0.0,
    val maxSupply: Double = 0.0,
    val marketCapUsd: Double = 0.0,
    val volumeUsd24Hr: Double = 0.0,
    val priceUsd: Double = 0.0,
    val changePercent24Hr: Double = 0.0,
    val vwap24Hr: Double = 0.0
)

