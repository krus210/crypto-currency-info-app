package ru.korolevss.main.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.korolevss.core_api.AppWithFacade
import ru.korolevss.main.R
import ru.korolevss.main.databinding.FragmentMainBinding
import ru.korolevss.main.di.MainFeatureComponent
import ru.korolevss.main.ui.vm.MainFragmentViewModel
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var viewModel : MainFragmentViewModel
    private val binding by viewBinding(FragmentMainBinding::bind)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainFeatureComponent.create((requireActivity().application as AppWithFacade).getFacade()).inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}