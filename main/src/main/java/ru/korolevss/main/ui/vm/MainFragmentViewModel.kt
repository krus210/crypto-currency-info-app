package ru.korolevss.main.ui.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.korolevss.main.domain.GetCoinAssetsUseCase
import ru.korolevss.main.domain.LoadCoinAssetsUseCase
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val getCoinAssetsUseCase: GetCoinAssetsUseCase,
    private val loadCoinAssetsUseCase: LoadCoinAssetsUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
}