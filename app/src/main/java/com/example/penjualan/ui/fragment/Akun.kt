package com.example.penjualan.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.penjualan.databinding.FragmentAkunBinding
import com.example.penjualan.model.Resource
import com.example.penjualan.network.UserApi
import com.example.penjualan.network.response.akun.Data
import com.example.penjualan.repository.UserRepository
import com.example.penjualan.ui.viewModel.AkunViewModel
import com.example.penjualan.util.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


class Akun : BaseFragment<AkunViewModel, FragmentAkunBinding, UserRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userPreferences.authToken.asLiveData().observe(viewLifecycleOwner) {
            if (it == null) findNavController().navigate(AkunDirections.actionAkunToLoginFragment())
        }

        binding.progressbar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner) {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    binding.progressbar.visible(false)
                    updateUI(it.value.user.data)
                    Log.d("akun", "${it.value.user.data}")
                }
                is Resource.Loading -> {
                    binding.progressbar.visible(true)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.logOut.setOnClickListener {
            logOut()
        }
    }

    private fun updateUI(data: Data) {
        with(binding){
            name.text = data.name
            emails.text = data.email
        }
    }

    override fun getViewModel() = AkunViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAkunBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }
}