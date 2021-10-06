package ru.korolevss.core_impl.network

import dagger.Component
import ru.korolevss.core_api.AppProvider
import ru.korolevss.core_api.network.NetworkProvider
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppProvider::class], modules = [NetworkModule::class])
interface NetworkComponent : NetworkProvider