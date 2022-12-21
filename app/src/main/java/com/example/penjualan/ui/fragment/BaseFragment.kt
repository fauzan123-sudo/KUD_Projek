package com.example.penjualan.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.penjualan.UserPreferences
import com.example.penjualan.network.RemoteDataSource
import com.example.penjualan.network.UserApi
import com.example.penjualan.repository.BaseRepository
import com.example.penjualan.ui.viewModel.BaseViewModel
import com.example.penjualan.ui.viewModel.ViewModelFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

abstract class BaseFragment<VM : BaseViewModel, B:ViewBinding, R:BaseRepository> : Fragment() {

    protected lateinit var userPreferences: UserPreferences
    protected lateinit var binding :B
    protected lateinit var viewModel : VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        userPreferences = UserPreferences(requireContext())
        binding         = getFragmentBinding(inflater, container)
        val factory     = ViewModelFactory(getFragmentRepository())
        viewModel       = ViewModelProvider(this, factory)[getViewModel()]
        lifecycleScope.launch {
            userPreferences.authToken.first()
        }
        return binding.root
    }

    fun logOut() = lifecycleScope.launch{
        val authToken = userPreferences.authToken.first()
        val api = remoteDataSource.buildApi(UserApi::class.java, authToken)
        viewModel.logOut(api)
        userPreferences.clear()
        Toast.makeText(requireContext(), "berhasil keluar", Toast.LENGTH_SHORT).show()

    }

    abstract fun getViewModel() : Class<VM>

    abstract fun getFragmentBinding(inflater:LayoutInflater, container: ViewGroup?) : B

    abstract fun getFragmentRepository() : R
}