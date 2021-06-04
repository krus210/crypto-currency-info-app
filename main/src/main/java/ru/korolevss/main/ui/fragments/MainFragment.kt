package ru.korolevss.main.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import ru.korolevss.core_api.AppWithFacade
import ru.korolevss.core_api.base.BaseFragment
import ru.korolevss.main.R
import ru.korolevss.main.databinding.FragmentMainBinding
import ru.korolevss.main.di.MainFeatureComponent
import ru.korolevss.main.ui.vm.MainFragmentViewModel
import ru.korolevss.main.ui.vm.MainViewModelFactory
import javax.inject.Inject

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private lateinit var viewModel : MainFragmentViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainFeatureComponent.create((requireActivity().application as AppWithFacade).getFacade()).inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }
}