package ru.korolevss.detail.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import extensions.safeUpdate
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.korolevss.core_api.dto.NoInternetError
import ru.korolevss.detail.domain.GetCoinCandlesUseCase
import ru.korolevss.detail.domain.models.CandleInterval
import ru.korolevss.detail.ui.DetailState

class DetailViewModel(
    private val coinAssetId: String,
    private val coroutineDispatcher: CoroutineDispatcher,
    private val getCoinCandlesUseCase: GetCoinCandlesUseCase
) : ViewModel() {

    private val _stateFlow = MutableStateFlow(DetailState(coinAssetId))
    val stateFlow = _stateFlow.asStateFlow()

    private var job : Job? = null

    init {
        job = viewModelScope.launch {
            loadCoinCandles()
        }
    }

    fun onRefresh() {
        job?.cancel()
        job = viewModelScope.launch {
            loadCoinCandles()
        }
    }

    fun onChangeInterval(interval : CandleInterval) {
        viewModelScope.launch {
            _stateFlow.safeUpdate { state -> state.copy(interval = interval) }
        }
    }

    fun onErrorDismiss() {
        viewModelScope.launch {
            _stateFlow.safeUpdate { state -> state.copy(hasNoInternetError = false, hasServererror = false) }
        }
    }

    private suspend fun loadCoinCandles() = withContext(coroutineDispatcher) {
        val result = getCoinCandlesUseCase.invoke(
            baseId = coinAssetId,
            interval = _stateFlow.value.interval
        )
        if (!isActive) return@withContext
        when (result) {
            GetCoinCandlesUseCase.Result.Loading -> _stateFlow.safeUpdate { state ->
                state.copy(
                    isLoading = true,
                    hasNoInternetError = false,
                    hasServererror = false
                )
            }
            is GetCoinCandlesUseCase.Result.Ok -> _stateFlow.safeUpdate { state ->
                state.copy(
                    coinCandles = result.data,
                    isLoading = false,
                    hasNoInternetError = false,
                    hasServererror = false
                )
            }
            is GetCoinCandlesUseCase.Result.Error -> when (result.ex) {
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

}