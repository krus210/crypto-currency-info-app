package ru.korolevss.detail.repository.interfaces

import ru.korolevss.core_api.dto.CoinAsset
import ru.korolevss.detail.domain.models.*

interface DetailRepository {

    suspend fun getCoinAsset(id : String) : CoinAsset

    suspend fun getCoinsHistory(id : String, interval : HistoryInterval) : List<CoinHistory>

    suspend fun getCoinMarkets(id : String, limit : Int? = null, offset : Int? = null) : List<CoinMarket>

    suspend fun getCoinCandles(settings : CoinCandleSettings) : List<CoinCandle>
}