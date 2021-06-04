package ru.korolevss.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import ru.korolevss.main.ui.vm.MainFragmentViewModel
import ru.korolevss.main.ui.vm.MainViewModelFactory
import kotlin.reflect.KClass

@Module
abstract class MainViewModelsModule {

    @Binds
    @MainFeatureScope
    abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun splashViewModel(viewModel: MainFragmentViewModel): ViewModel

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

}