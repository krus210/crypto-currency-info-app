package ru.korolevss.main.repository.interfaces

import ru.korolevss.core_api.dto.CoinAsset

interface MainRepository {

    suspend fun getCoinAssets() : List<CoinAsset>
}