package ru.korolevss.main.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.korolevss.main.ui.vm.MainFragmentViewModel
import ru.korolevss.main.ui.vm.MainViewModelFactory
import kotlin.reflect.KClass

@Module(includes = [MainViewModelsModule.BindsModule::class])
object MainViewModelsModule {

    @Provides
    fun provideCoroutineDispatcher() : CoroutineDispatcher = Dispatchers.IO

    @Module
    interface BindsModule {
        @Binds
        @MainFeatureScope
        fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory

        @Binds
        @IntoMap
        @ViewModelKey(MainFragmentViewModel::class)
        fun mainViewModel(viewModel: MainFragmentViewModel): ViewModel
    }

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

}