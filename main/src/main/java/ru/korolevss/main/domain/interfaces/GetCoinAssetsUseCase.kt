package ru.korolevss.main.domain.interfaces

import kotlinx.coroutines.flow.Flow
import ru.korolevss.main.domain.Result

interface GetCoinAssetsUseCase {

    fun get() : Flow<Result>
}

