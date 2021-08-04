package ru.korolevss.core_impl.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun provideMediaType(): MediaType = "application/json".toMediaType()

    @Provides
    @Singleton
    fun provideNetworkClient(): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor())
        .build()

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, json : Json, mediaType : MediaType) : Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.coincap.io/v2/")
        .addConverterFactory(json.asConverterFactory(mediaType))
        .build()
}