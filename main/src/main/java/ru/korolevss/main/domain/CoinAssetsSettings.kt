package ru.korolevss.main.domain

data class CoinAssetsSettings(
    val searchId: String? = null,
    val searchIds: List<String>? = null,
    val limit: Int? = null,
    val offset: Int? = null,
)
