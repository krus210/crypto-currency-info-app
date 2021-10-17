package ru.korolevss.detail.di

import dagger.Component
import ru.korolevss.core_api.ProvidersFacade
import ru.korolevss.detail.ui.frgments.DetailFragment
import javax.inject.Scope

@Component(
    modules = [DetailNetworkApiModule::class, DetailDomainModule::class, DetailCoroutineDispatcherModule::class],
    dependencies =[ProvidersFacade::class]
)
@DetailFeatureScope
interface DetailFeatureComponent {

    companion object {

        fun create(providersFacade: ProvidersFacade): DetailFeatureComponent =
            DaggerDetailFeatureComponent
                .builder()
                .providersFacade(providersFacade)
                .build()
    }

    fun inject(detailFragment : DetailFragment)

}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class DetailFeatureScope