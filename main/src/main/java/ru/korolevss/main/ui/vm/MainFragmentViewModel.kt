package ru.korolevss.main.ui.vm

import androidx.lifecycle.ViewModel
import ru.korolevss.main.domain.interfaces.GetCoinAssetsUseCase
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(getCoinAssetsUseCase: GetCoinAssetsUseCase) : ViewModel() {
}