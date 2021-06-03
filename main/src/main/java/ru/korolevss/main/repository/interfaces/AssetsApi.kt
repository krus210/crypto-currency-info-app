package ru.korolevss.main.repository.interfaces

import retrofit2.http.GET
import ru.korolevss.main.repository.models.DataResponse

interface AssetsApi {

    @GET("assets")
    suspend fun getAssets(): DataResponse
}