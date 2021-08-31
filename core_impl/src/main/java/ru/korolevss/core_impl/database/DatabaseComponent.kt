package ru.korolevss.core_impl.database

import dagger.Component
import ru.korolevss.core_api.AppProvider
import ru.korolevss.core_api.database.DatabaseProvider
import javax.inject.Singleton

@Singleton
@Component(dependencies = [AppProvider::class], modules = [DatabaseModule::class])
interface DatabaseComponent : DatabaseProvider