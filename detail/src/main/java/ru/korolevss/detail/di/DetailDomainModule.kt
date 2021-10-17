package ru.korolevss.detail.di

import dagger.Module
import dagger.Provides
import ru.korolevss.detail.domain.GetCoinCandlesUseCase
import ru.korolevss.detail.repository.interfaces.DetailRepository

@Module
object DetailDomainModule {

    @Provides
    @DetailFeatureScope
    fun provideGetCoinCandlesUseCase(detailRepository: DetailRepository): GetCoinCandlesUseCase = GetCoinCandlesUseCase(detailRepository)
}