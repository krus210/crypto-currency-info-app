package ru.korolevss.main.domain.implementations

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.korolevss.main.domain.Result
import ru.korolevss.main.domain.interfaces.GetCoinAssetsUseCase
import ru.korolevss.main.repository.interfaces.MainRepository
import javax.inject.Inject

class GetCoinAssetsUseCaseImpl @Inject constructor(val repository: MainRepository) : GetCoinAssetsUseCase {

    override fun get(): Flow<Result> {
        return flowOf(Result.Error("TODO"))
    }
}