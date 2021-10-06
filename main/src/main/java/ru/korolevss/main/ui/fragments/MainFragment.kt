package ru.korolevss.main.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ru.korolevss.compose_general.theme.CryptoCurrencyInfoApp
import ru.korolevss.core_api.AppWithFacade
import ru.korolevss.main.R
import ru.korolevss.main.di.MainFeatureComponent
import ru.korolevss.main.ui.compose.MainScreen
import ru.korolevss.main.ui.vm.MainFragmentViewModel
import ru.korolevss.navigation.NavGraphDirections
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewModel: MainFragmentViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainFeatureComponent.create((requireActivity().application as AppWithFacade).getFacade()).inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CryptoCurrencyInfoApp {
                    val uiState by viewModel.stateFlow.collectAsState()

                    MainScreen(
                        uiState = uiState,
                        openCoinAsset = { coinAssetId ->
                            findNavController().navigate(NavGraphDirections.openDetailGraph(coinAssetId))
                        },
                        openSearch = { /*TODO*/ },
                        openSettings = { /*TODO*/ },
                        onRefresh = { viewModel.onRefresh() },
                        onLoadNewCoinAssets = { viewModel.onRefresh() },
                        onErrorDismiss = { viewModel.onErrorDismiss() },
                        mainScaffoldState = rememberScaffoldState()
                    )
                }
            }
        }
    }
}