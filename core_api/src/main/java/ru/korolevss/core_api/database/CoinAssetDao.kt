package ru.korolevss.core_api.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.korolevss.core_api.dto.CoinAsset

@Dao
interface CoinAssetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(coinAssets : List<CoinAsset>)

    @Query("DELETE FROM coin_assets")
    suspend fun clearAll()

    @Query("SELECT * FROM coin_assets")
    fun getAll() : Flow<List<CoinAsset>>

    @Query("SELECT COUNT(*) FROM coin_assets")
    fun getSize() : Flow<Int>

    @Query("SELECT * FROM coin_assets WHERE id = :id")
    fun get(id : String) : Flow<CoinAsset>

    fun getDistinctUntilChanged(id : String) = get(id).distinctUntilChanged()
}