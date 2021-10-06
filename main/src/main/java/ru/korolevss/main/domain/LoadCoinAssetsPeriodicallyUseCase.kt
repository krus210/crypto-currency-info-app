package ru.korolevss.main.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import ru.korolevss.main.repository.interfaces.MainRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoadCoinAssetsPeriodicallyUseCase @Inject constructor(
    private val repository : MainRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun invoke(): Flow<Result> = flow {
        while (true) {
            emit(Result.Loading)
            repository.loadCoinAssets()
            emit(Result.Ok as Result)
            delay(TimeUnit.MINUTES.toMillis(15))
        }
    }.catch { ex : Throwable -> emit(Result.Error(ex)) }

    sealed class Result {
        object Ok : Result()
        object Loading : Result()
        data class Error(val ex : Throwable) : Result()
    }
}