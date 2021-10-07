package ru.korolevss.detail.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import ru.korolevss.detail.domain.GetCoinCandlesUseCase

class DetailViewModelFactory @AssistedInject constructor(
    @Assisted("coinAssetId") private val coinAssetId: String,
    private val coroutineDispatcher : CoroutineDispatcher,
    private val getCoinCandlesUseCase: GetCoinCandlesUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == DetailViewModel::class.java)
        return DetailViewModel(coinAssetId, coroutineDispatcher, getCoinCandlesUseCase) as T
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("coinAssetId") coinAssetId: String): DetailViewModelFactory
    }
}