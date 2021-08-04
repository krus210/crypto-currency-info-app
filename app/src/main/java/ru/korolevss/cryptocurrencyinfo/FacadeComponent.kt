package ru.korolevss.cryptocurrencyinfo

import android.app.Application
import dagger.Component
import ru.korolevss.core.CoreProvidersFactory
import ru.korolevss.core_api.AppProvider
import ru.korolevss.core_api.ProvidersFacade
import ru.korolevss.core_api.network.NetworkProvider

@Component(dependencies = [AppProvider::class, NetworkProvider::class])
interface FacadeComponent : ProvidersFacade {

    companion object {

        fun init(application: Application) : FacadeComponent =
            DaggerFacadeComponent.builder()
                .appProvider(AppComponent.create(application))
                .networkProvider(CoreProvidersFactory.createNetworkBuilder())
                .build()
    }

    fun inject(app: App)
}