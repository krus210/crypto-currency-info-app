package ru.korolevss.detail.repository.implementations

import ru.korolevss.core_api.dto.CoinAsset
import ru.korolevss.detail.domain.models.*
import ru.korolevss.detail.repository.interfaces.DetailApi
import ru.korolevss.detail.repository.interfaces.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val api: DetailApi
) : DetailRepository {
    override suspend fun getCoinAsset(id: String): CoinAsset {
        TODO("Not yet implemented")
    }

    override suspend fun getCoinsHistory(id: String, interval: HistoryInterval): List<CoinHistory> {
        TODO("Not yet implemented")
    }

    override suspend fun getCoinMarkets(id: String, limit: Int?, offset: Int?): List<CoinMarket> {
        TODO("Not yet implemented")
    }

    override suspend fun getCoinCandles(settings: CoinCandleSettings): List<CoinCandle> =
        api.getCandles(
            exchangeId = settings.exchangeId,
            interval = settings.interval.value,
            baseId = settings.baseId,
            quoteId = settings.quoteId
        ).data.map { coinCandleResponse -> coinCandleResponse.toCoinCandle() }


}