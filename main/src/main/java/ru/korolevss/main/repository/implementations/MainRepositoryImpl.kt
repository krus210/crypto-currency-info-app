package ru.korolevss.main.repository.implementations

import ru.korolevss.core_api.dto.CoinAsset
import ru.korolevss.main.repository.interfaces.AssetsApi
import ru.korolevss.main.repository.interfaces.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(val api: AssetsApi) : MainRepository {


    override suspend fun getCoinAssets(): List<CoinAsset> =
        api.getAssets().data.map {
            CoinAsset(
                it.summary,
                it.rank,
                it.symbol,
                it.name,
                it.supply,
                it.maxSupply,
                it.marketCapUsd,
                it.volumeUsd24Hr,
                it.priceUsd,
                it.changePercent24Hr,
                it.vwap24Hr
            )
        }

}