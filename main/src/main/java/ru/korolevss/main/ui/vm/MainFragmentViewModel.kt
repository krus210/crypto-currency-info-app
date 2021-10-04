package ru.korolevss.main.ui.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.korolevss.main.domain.GetCoinAssetsUseCase
import ru.korolevss.main.domain.LoadCoinAssetsPeriodicallyUseCase
import ru.korolevss.main.domain.LoadCoinAssetsUseCase
import ru.korolevss.main.ui.MainState
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val getCoinAssetsUseCase: GetCoinAssetsUseCase,
    private val loadCoinAssetsUseCase: LoadCoinAssetsUseCase,
    private val loadCoinAssetsPeriodicallyUseCase: LoadCoinAssetsPeriodicallyUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(MainState())
    val stateFlow = _stateFlow.asStateFlow()
}