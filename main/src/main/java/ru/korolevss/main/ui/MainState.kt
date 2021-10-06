package ru.korolevss.main.ui

import ru.korolevss.core_api.dto.CoinAsset

data class MainState(
    val coinAssets : List<CoinAsset> = emptyList(),
    val isLoading : Boolean = false,
    val hasServererror : Boolean = false,
    val hasNoInternetError : Boolean = false
) {
    val initialLoad: Boolean
        get() = coinAssets.isEmpty() && !hasServererror && !hasNoInternetError && isLoading
}