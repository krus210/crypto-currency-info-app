package ru.korolevss.main.ui

import ru.korolevss.core_api.dto.CoinAsset

data class MainState(
    val coinAssets : List<CoinAsset> = emptyList(),
    val isLoading : Boolean = false,
    val needShowErrorDialog : Boolean = false
)