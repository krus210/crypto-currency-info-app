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
    fun invoke(): Flow<Result<Unit>> = flow {
        while (true) {
            delay(TimeUnit.MINUTES.toMillis(15))
            emit(Result.success(repository.loadCoinAssets()))
        }
    }.catch { ex : Throwable -> emit(Result.failure(ex)) }
}