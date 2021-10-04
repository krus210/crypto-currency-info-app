package ru.korolevss.core_api.database

interface DatabaseProvider {

    fun provideDatabase(): CoinAssetDatabaseContract

    fun provideCoinAssetDao() : CoinAssetDao
}