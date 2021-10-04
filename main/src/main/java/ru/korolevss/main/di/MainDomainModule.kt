package ru.korolevss.main.di

import dagger.Binds
import dagger.Module
import ru.korolevss.main.domain.GetCoinAssetsUseCase
import ru.korolevss.main.domain.LoadCoinAssetsPeriodicallyUseCase
import ru.korolevss.main.repository.implementations.MainRepositoryImpl
import ru.korolevss.main.repository.interfaces.MainRepository

@Module
abstract class MainDomainModule {

    @Binds
    @MainFeatureScope
    abstract fun bindsGetCoinAssetsUseCase(getCoinAssetsUseCase: GetCoinAssetsUseCase): GetCoinAssetsUseCase

    @Binds
    @MainFeatureScope
    abstract fun bindsLoadCoinAssetsUseCase(loadCoinAssetsPeriodicallyUseCase: LoadCoinAssetsPeriodicallyUseCase): LoadCoinAssetsPeriodicallyUseCase

    @Binds
    @MainFeatureScope
    abstract fun bindsRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}