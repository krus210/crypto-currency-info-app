package ru.korolevss.detail.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.korolevss.core_api.ProvidersFacade
import ru.korolevss.detail.repository.implementations.DetailRepositoryImpl
import ru.korolevss.detail.repository.interfaces.DetailApi
import ru.korolevss.detail.repository.interfaces.DetailRepository

@Module(includes = [DetailNetworkApiModule.BindsModule::class])
object DetailNetworkApiModule {

    @Provides
    @DetailFeatureScope
    fun provideApi(providersFacade: ProvidersFacade): DetailApi = providersFacade.provideNetworkRetrofit().create(DetailApi::class.java)

    @Module
    interface BindsModule {

        @Binds
        @DetailFeatureScope
        fun bindDetailRepository(detailRepositoryImpl: DetailRepositoryImpl) : DetailRepository
    }
}