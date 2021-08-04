package ru.korolevss.main.repository.interfaces

import retrofit2.http.GET
import retrofit2.http.Query
import ru.korolevss.main.repository.models.DataResponse

interface AssetsApi {

    @GET("assets")
    suspend fun getAssets(
        @Query("search") search : String?,
        @Query("ids") ids : String?,
        @Query("limit") limit : Int?,
        @Query("offset") offset : Int?
    ): DataResponse
}