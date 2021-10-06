package ru.korolevss.core_impl.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.Reusable
import ru.korolevss.core_api.database.CoinAssetDao
import ru.korolevss.core_api.database.CoinAssetDatabaseContract
import javax.inject.Singleton

private const val DATABASE_NAME = "COIN_ASSET_DATABASE"

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCoinAssetDatabase(context: Context): CoinAssetDatabaseContract {
        return Room.databaseBuilder(
            context,
            CoinAssetDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    @Reusable
    fun provideHabitsDao(coinAssetDatabaseContract: CoinAssetDatabaseContract): CoinAssetDao {
        return coinAssetDatabaseContract.provideCoinAssetDao()
    }
}