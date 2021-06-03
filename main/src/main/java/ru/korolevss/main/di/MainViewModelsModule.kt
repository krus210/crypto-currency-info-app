package ru.korolevss.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import ru.korolevss.main.domain.interfaces.GetCoinAssetsUseCase
import ru.korolevss.main.ui.vm.MainFragmentViewModel
import ru.korolevss.main.ui.vm.MainViewModelFactoryProvider

@Module
class MainViewModelsModule {

    @Provides
    @MainFeatureScope
    fun provideViewModelHolder(): @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel> = mutableMapOf()

    @Provides
    @MainFeatureScope
    fun bindsFactory(map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>): ViewModelProvider.Factory =
        MainViewModelFactoryProvider(map)


    @Provides
    @MainFeatureScope
    fun provideMainFragmentViewModule(
        map: @JvmSuppressWildcards MutableMap<Class<out ViewModel>, ViewModel>,
        getCoinAssetsUseCase: GetCoinAssetsUseCase
    ): ViewModel = MainFragmentViewModel(getCoinAssetsUseCase).also {
        map[MainFragmentViewModel::class.java] = it
    }


}