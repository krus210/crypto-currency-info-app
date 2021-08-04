package ru.korolevss.detail.ui.frgments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.korolevss.detail.R
import ru.korolevss.detail.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val args by navArgs<DetailFragmentArgs>()
    private val binding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}