package ru.korolevss.detail.ui.frgments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import ru.korolevss.core_api.base.BaseFragment
import ru.korolevss.detail.R
import ru.korolevss.detail.databinding.FragmentDetailBinding

class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {

    private val args by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
    }

}