package ru.korolevss.core

import ru.korolevss.core_api.network.NetworkProvider
import ru.korolevss.core_impl.network.DaggerNetworkComponent

object CoreProvidersFactory {

    fun createNetworkBuilder() : NetworkProvider = DaggerNetworkComponent.builder().build()

}