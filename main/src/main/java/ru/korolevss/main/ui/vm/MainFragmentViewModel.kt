package ru.korolevss.main.ui.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import extensions.emitWithScope
import extensions.safeUpdate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.korolevss.core_api.dto.NoInternetError
import ru.korolevss.main.domain.GetCoinAssetsUseCase
import ru.korolevss.main.domain.LoadCoinAssetsPeriodicallyUseCase
import ru.korolevss.main.domain.LoadCoinAssetsPeriodicallyUseCase.Result
import ru.korolevss.main.ui.MainState
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val loadCoinAssetsPeriodicallyUseCase: LoadCoinAssetsPeriodicallyUseCase,
    getCoinAssetsUseCase: GetCoinAssetsUseCase,
    coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(MainState())
    val stateFlow = _stateFlow.asStateFlow()

    private val startLoadCoinAssets = MutableSharedFlow<Unit>()

    @ExperimentalCoroutinesApi
    private val loadCoinAssetsFlow = startLoadCoinAssets.flatMapLatest {
        loadCoinAssetsPeriodicallyUseCase.invoke()
    }
        .flowOn(coroutineDispatcher)
        .onEach { result: Result ->
            when (result) {
                Result.Ok -> _stateFlow.safeUpdate { state -> state.copy(isLoading = false, hasNoInternetError = false, hasServererror = false) }
                Result.Loading -> _stateFlow.safeUpdate { state -> state.copy(isLoading = true, hasNoInternetError = false, hasServererror = false) }
                is Result.Error -> when (result.ex) {
                    is NoInternetError -> _stateFlow.safeUpdate { state ->
                        state.copy(
                            isLoading = false,
                            hasNoInternetError = true,
                            hasServererror = false
                        )
                    }
                    else -> _stateFlow.safeUpdate { state -> state.copy(isLoading = false, hasNoInternetError = false, hasServererror = true) }
                }
            }
        }
        .shareIn(scope = viewModelScope, started = SharingStarted.Eagerly)

    private val coinAssetsFlow = getCoinAssetsUseCase.invoke()
        .flowOn(coroutineDispatcher)
        .onEach { coinAssets ->
            _stateFlow.safeUpdate { state -> state.copy(coinAssets = coinAssets) }
        }
        .shareIn(scope = viewModelScope, started = SharingStarted.Eagerly)

    init {
        startLoadCoinAssets.emitWithScope(viewModelScope, Unit)
    }

    fun onRefresh() {
        viewModelScope.launch {
            _stateFlow.safeUpdate { state -> state.copy(hasNoInternetError = false, hasServererror = false) }
        }
        startLoadCoinAssets.emitWithScope(viewModelScope, Unit)
    }

    fun onErrorDismiss() {
        viewModelScope.launch {
            _stateFlow.safeUpdate { state -> state.copy(hasNoInternetError = false, hasServererror = false) }
        }
    }
}