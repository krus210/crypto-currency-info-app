package ru.korolevss.detail.repository.interfaces

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.korolevss.detail.repository.models.CoinsHistoryResponse
import ru.korolevss.detail.repository.models.DataCoinCandlesResponse
import ru.korolevss.detail.repository.models.DataCoinMarketsResponse
import ru.korolevss.detail.repository.models.DataCoinResponse

interface DetailApi {

    @GET("assets/{id}")
    suspend fun getAsset(
        @Path("id") id: String
    ): DataCoinResponse

    @GET("assets/{id}/history")
    suspend fun getHistory(
        @Path("id") id: String,
        @Query("interval") interval : String
    ): CoinsHistoryResponse

    @GET("assets/{id}/markets")
    suspend fun getMarkets(
        @Path("id") id: String,
        @Query("limit") limit : Int?,
        @Query("offset") offset : Int?
    ): DataCoinMarketsResponse

    @GET("candles")
    suspend fun getCandles(
        @Query("exchange") exchangeId : String,
        @Query("interval") interval : String,
        @Query("baseId") baseId : String,
        @Query("quoteId") quoteId : String
    ): DataCoinCandlesResponse


}