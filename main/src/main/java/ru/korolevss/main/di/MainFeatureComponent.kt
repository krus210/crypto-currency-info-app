package ru.korolevss.main.di

import dagger.Component
import ru.korolevss.core_api.ProvidersFacade
import ru.korolevss.main.ui.fragments.MainFragment
import javax.inject.Scope


@Component(modules = [MainNetworkApiModule::class, MainDomainModule::class, MainViewModelsModule::class],
    dependencies =[ProvidersFacade::class]
)
@MainFeatureScope
interface MainFeatureComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade) =
            DaggerMainFeatureComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
    }

    fun inject(mainFragment : MainFragment)

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainFeatureScope