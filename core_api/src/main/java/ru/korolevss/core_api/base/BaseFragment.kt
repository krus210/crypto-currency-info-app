package ru.korolevss.core_api.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open class BaseFragment<T : ViewDataBinding>(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected lateinit var binding : T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.bind<T>(view!!)!!
        return view
    }
}