package ru.korolevss.core

import ru.korolevss.core_api.AppProvider
import ru.korolevss.core_api.database.DatabaseProvider
import ru.korolevss.core_api.network.NetworkProvider
import ru.korolevss.core_impl.database.DaggerDatabaseComponent
import ru.korolevss.core_impl.network.DaggerNetworkComponent

object CoreProvidersFactory {

    fun createNetworkBuilder(appProvider: AppProvider): NetworkProvider = DaggerNetworkComponent.builder().appProvider(appProvider).build()

    fun createDatabaseBuilder(appProvider: AppProvider): DatabaseProvider =
        DaggerDatabaseComponent.builder().appProvider(appProvider).build()
}