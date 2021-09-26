package ru.korolevss.main.repository.interfaces

import kotlinx.coroutines.flow.Flow
import ru.korolevss.core_api.dto.CoinAsset

interface MainRepository {
    fun loadCoinAssets(): Flow<Result<Unit>>
    fun refreshCoinAssets(): Flow<Result<Unit>>
    fun getCoinAssets(): Flow<Result<List<CoinAsset>>>
}