package ru.korolevss.detail.ui.frgments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.accompanist.pager.ExperimentalPagerApi
import ru.korolevss.compose_general.theme.CryptoCurrencyInfoApp
import ru.korolevss.compose_general.theme.CryptoCurrencyInfoTheme
import ru.korolevss.core_api.AppWithFacade
import ru.korolevss.detail.R
import ru.korolevss.detail.di.DetailFeatureComponent
import ru.korolevss.detail.ui.compose.DetailScreen
import ru.korolevss.detail.ui.vm.DetailViewModel
import ru.korolevss.detail.ui.vm.DetailViewModelFactory
import ru.korolevss.navigation.NavGraphDirections
import javax.inject.Inject

class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by viewModels {
        factory.create(args.id)
    }

    @Inject
    lateinit var factory: DetailViewModelFactory.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DetailFeatureComponent.create((requireActivity().application as AppWithFacade).getFacade()).inject(this)
    }

    @ExperimentalPagerApi
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                CryptoCurrencyInfoTheme {
                    val uiState by viewModel.stateFlow.collectAsState()

                    DetailScreen(
                        uiState = uiState,
                        onRefresh = { viewModel.onRefresh() } ,
                        onBack = { requireActivity().onBackPressed() },
                        onErrorDismiss = { viewModel.onErrorDismiss() },
                        scaffoldState = rememberScaffoldState()
                    )
                }
            }
        }
    }


}