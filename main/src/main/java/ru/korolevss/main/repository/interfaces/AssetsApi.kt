package ru.korolevss.main.repository.interfaces

import retrofit2.http.GET
import retrofit2.http.Query
import ru.korolevss.main.repository.models.DataResponse

interface AssetsApi {

    @GET("assets")
    suspend fun getAssets(
        @Query("search") search : String? = null,
        @Query("ids") ids : String? = null,
        @Query("limit") limit : Int? = null,
        @Query("offset") offset : Int? = null
    ): DataResponse
}