package ru.korolevss.detail.domain

import ru.korolevss.detail.domain.models.CandleInterval
import ru.korolevss.detail.domain.models.CoinCandle
import ru.korolevss.detail.domain.models.CoinCandleSettings
import ru.korolevss.detail.repository.interfaces.DetailRepository
import javax.inject.Inject

class GetCoinCandlesUseCase @Inject constructor(
    private val repository: DetailRepository
) {

    suspend fun invoke(baseId: String, interval: CandleInterval): Result {
        return try {
            Result.Loading
            val coinCandles = repository.getCoinCandles(CoinCandleSettings(interval = interval, baseId = baseId))
            Result.Ok(coinCandles)
        } catch (ex: Exception) {
            Result.Error(ex)
        }
    }

    sealed class Result {
        data class Ok(val data: List<CoinCandle>) : Result()
        object Loading : Result()
        data class Error(val ex: Throwable) : Result()
    }
}