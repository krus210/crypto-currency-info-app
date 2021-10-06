package ru.korolevss.main.repository.implementations

import kotlinx.coroutines.flow.Flow
import ru.korolevss.core_api.database.CoinAssetDao
import ru.korolevss.core_api.dto.CoinAsset
import ru.korolevss.main.repository.interfaces.AssetsApi
import ru.korolevss.main.repository.interfaces.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: AssetsApi,
    private val dao: CoinAssetDao
) : MainRepository {

    override suspend fun loadCoinAssets() {
        val offset = dao.getSize()
        val newCoinAssets =
            api.getAssets(
                limit = offset + LIMIT
            ).data.map { coinAsset -> coinAsset.mapToCoinAsset() }
        dao.saveAll(newCoinAssets)
    }

    override fun getCoinAssets(): Flow<List<CoinAsset>> = dao.getAll()

    private companion object {
        const val LIMIT = 10
    }
}