package ru.korolevss.main.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


class MainViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>,
        @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T
}