package ru.korolevss.main.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.korolevss.main.domain.GetCoinAssetsUseCase
import ru.korolevss.main.domain.LoadCoinAssetsPeriodicallyUseCase
import ru.korolevss.main.repository.implementations.MainRepositoryImpl
import ru.korolevss.main.repository.interfaces.MainRepository

@Module(includes = [MainDomainModule.BindsModule::class])
object MainDomainModule {

    @Provides
    @MainFeatureScope
    fun bindsGetCoinAssetsUseCase(mainRepository: MainRepository): GetCoinAssetsUseCase = GetCoinAssetsUseCase(mainRepository)

    @Provides
    @MainFeatureScope
    fun bindsLoadCoinAssetsUseCase(mainRepository: MainRepository): LoadCoinAssetsPeriodicallyUseCase =
        LoadCoinAssetsPeriodicallyUseCase(mainRepository)

    @Module
    interface BindsModule {
        @Binds
        @MainFeatureScope
        fun bindsRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
    }
}