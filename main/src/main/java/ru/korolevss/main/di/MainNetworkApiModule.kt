package ru.korolevss.main.di

import dagger.Module
import dagger.Provides
import ru.korolevss.core_api.ProvidersFacade
import ru.korolevss.main.repository.interfaces.AssetsApi

@Module
object MainNetworkApiModule {

    @Provides
    @MainFeatureScope
    fun provideApi(providersFacade: ProvidersFacade): AssetsApi = providersFacade.provideNetworkRetrofit().create(AssetsApi::class.java)
}