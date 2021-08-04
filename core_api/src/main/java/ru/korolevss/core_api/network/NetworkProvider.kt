package ru.korolevss.core_api.network

import retrofit2.Retrofit

interface NetworkProvider {

    fun provideNetworkRetrofit() : Retrofit
}