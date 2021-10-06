package ru.korolevss.main.domain

import ru.korolevss.main.repository.interfaces.MainRepository
import javax.inject.Inject

class LoadCoinAssetsUseCase @Inject constructor(
    val repository: MainRepository
) {

    suspend fun invoke(): Result<Unit> = runCatching {
        repository.loadCoinAssets()
    }

}