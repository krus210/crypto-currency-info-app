package ru.korolevss.main.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import ru.korolevss.main.repository.interfaces.MainRepository
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoadCoinAssetsUseCase @Inject constructor(
    private val repository : MainRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun invoke() = flow {
        while (true) {
            delay(TimeUnit.MINUTES.toMillis(15))
            emit(Unit)
        }
    }.flatMapLatest { repository.loadCoinAssets() }
}