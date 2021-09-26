package ru.korolevss.main.repository.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.korolevss.core_api.database.CoinAssetDao
import ru.korolevss.core_api.dto.CoinAsset
import ru.korolevss.main.repository.interfaces.AssetsApi
import ru.korolevss.main.repository.interfaces.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: AssetsApi,
    private val dao: CoinAssetDao
) : MainRepository {

    override fun loadCoinAssets(): Flow<Result<Unit>> = dao.getSize()
        .map { offset ->
            dao.clearAll()
            val newCoinAssets =
                api.getAssets(
                    limit = offset + LIMIT
                ).data.map { coinAsset -> coinAsset.mapToCoinAsset() }
            dao.saveAll(newCoinAssets)
            Result.success(Unit)
        }.catch { ex -> emit(Result.failure(ex)) }


    override fun refreshCoinAssets(): Flow<Result<Unit>> = flow {
        dao.clearAll()
        val newCoinAssets =
            api.getAssets(
                limit = LIMIT
            ).data.map { coinAsset -> coinAsset.mapToCoinAsset() }
        dao.saveAll(newCoinAssets)
        emit(Result.success(Unit))
    }.catch { ex -> emit(Result.failure(ex)) }

    override fun getCoinAssets(): Flow<Result<List<CoinAsset>>> = dao.getAll()
        .map { coinAssets -> Result.success(coinAssets) }
        .catch { ex -> emit(Result.failure(ex)) }

    private companion object {
        const val LIMIT = 10
    }
}