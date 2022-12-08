package com.example.penjualan.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.penjualan.UserPreferences
import com.example.penjualan.network.RemoteDataSource
import com.example.penjualan.repository.BaseRepository
import com.example.penjualan.ui.viewModel.ViewModelFactory

abstract class BaseFragment<VM : ViewModel, B:ViewBinding, R:BaseRepository> : Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding :B
    protected lateinit var viewModel : VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        userPreferences = UserPreferences(requireContext())
        binding     = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel   = ViewModelProvider(this, factory)[getViewModel()]
        return binding.root
    }

    abstract fun getViewModel() : Class<VM>

    abstract fun getFragmentBinding(inflater:LayoutInflater, container: ViewGroup?) : B

    abstract fun getFragmentRepository() : R
}