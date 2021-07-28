package ru.korolevss.main.repository.interfaces

import ru.korolevss.core_api.dto.CoinAsset
import ru.korolevss.main.domain.CoinAssetsSettings

interface MainRepository {

    suspend fun getCoinAssets(settings : CoinAssetsSettings = CoinAssetsSettings()) : List<CoinAsset>
}