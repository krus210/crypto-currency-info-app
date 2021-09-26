package ru.korolevss.main.domain

import ru.korolevss.main.repository.interfaces.MainRepository
import javax.inject.Inject

class GetCoinAssetsUseCase @Inject constructor(val repository: MainRepository) {
    fun invoke() = repository.getCoinAssets()
}