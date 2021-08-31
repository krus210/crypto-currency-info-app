package ru.korolevss.core_impl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.korolevss.core_api.database.CoinAssetDatabaseContract
import ru.korolevss.core_api.dto.CoinAsset

@Database(entities = [CoinAsset::class], version = 1, exportSchema = false)
abstract class CoinAssetDatabase : RoomDatabase(), CoinAssetDatabaseContract