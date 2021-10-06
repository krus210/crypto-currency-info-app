package ru.korolevss.core_api.database

interface CoinAssetDatabaseContract {
    fun provideCoinAssetDao() : CoinAssetDao
}