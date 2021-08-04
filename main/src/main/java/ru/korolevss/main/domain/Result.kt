package ru.korolevss.main.domain

import ru.korolevss.core_api.dto.CoinAsset

sealed class Result {
    data class Success(val data: List<CoinAsset>) : Result()
    object Loading : Result()
    data class Error(val message: String) : Result()
}
