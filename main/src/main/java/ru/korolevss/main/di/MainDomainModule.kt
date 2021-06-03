package ru.korolevss.main.di

import dagger.Binds
import dagger.Module
import ru.korolevss.main.domain.implementations.GetCoinAssetsUseCaseImpl
import ru.korolevss.main.domain.interfaces.GetCoinAssetsUseCase
import ru.korolevss.main.repository.implementations.MainRepositoryImpl
import ru.korolevss.main.repository.interfaces.MainRepository

@Module
abstract class MainDomainModule {

    @Binds
    @MainFeatureScope
    abstract fun bindsGetCoinAssetsUseCase(getCoinAssetsUseCaseImpl: GetCoinAssetsUseCaseImpl): GetCoinAssetsUseCase

    @Binds
    @MainFeatureScope
    abstract fun bindsRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}