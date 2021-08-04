package ru.korolevss.cryptocurrencyinfo

import android.app.Application
import ru.korolevss.core_api.AppWithFacade
import ru.korolevss.core_api.ProvidersFacade

class App : Application(), AppWithFacade  {

    companion object {

        private var facadeComponent : FacadeComponent? = null
    }

    override fun getFacade(): ProvidersFacade =
        facadeComponent ?: FacadeComponent.init(this).also { facadeComponent = it }

    override fun onCreate() {
        super.onCreate()
        (getFacade() as FacadeComponent).inject(this)
    }
}