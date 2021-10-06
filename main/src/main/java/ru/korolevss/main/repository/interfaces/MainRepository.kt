package ru.korolevss.main.repository.interfaces

import kotlinx.coroutines.flow.Flow
import ru.korolevss.core_api.dto.CoinAsset

interface MainRepository {
    suspend fun loadCoinAssets()
    fun getCoinAssets(): Flow<List<CoinAsset>>
}