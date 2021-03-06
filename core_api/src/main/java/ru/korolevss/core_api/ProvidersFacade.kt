package ru.korolevss.core_api

import ru.korolevss.core_api.database.DatabaseProvider
import ru.korolevss.core_api.network.NetworkProvider

interface ProvidersFacade : NetworkProvider, AppProvider, DatabaseProvider