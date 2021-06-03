package ru.korolevss.main.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.korolevss.core_api.ProvidersFacade
import ru.korolevss.main.repository.implementations.MainRepositoryImpl
import ru.korolevss.main.repository.interfaces.AssetsApi
import ru.korolevss.main.repository.interfaces.MainRepository

@Module
class MainNetworkApiModule {

    @Provides
    @MainFeatureScope
    fun provideApi(providersFacade: ProvidersFacade): AssetsApi = providersFacade.provideNetworkRetrofit().create(AssetsApi::class.java)
}