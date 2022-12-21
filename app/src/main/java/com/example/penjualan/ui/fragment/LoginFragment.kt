package com.example.penjualan.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.penjualan.R
import com.example.penjualan.databinding.FragmentLoginBinding
import com.example.penjualan.model.Resource
import com.example.penjualan.network.ApiInterFace
import com.example.penjualan.repository.AuthRepository
import com.example.penjualan.ui.viewModel.AuthViewModel
import com.example.penjualan.util.handleApiError
import com.example.penjualan.util.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)

        viewModel.loginResponse.observe(viewLifecycleOwner) {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        Toast.makeText(requireContext(), "berhasil login", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("login", "$it")

//                        requireActivity().onBackPressed()
                        val action = LoginFragmentDirections.actionLoginFragmentToAkun()

                        findNavController().navigate(action)


//                    Navigation.findNavController(requireActivity()).
                        viewModel.saveAuthToken(it.value.user.access_token!!)

//                            requireActivity().startNewActivity(MainActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        }

        binding.login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email    = binding.email.text.toString().trim()
        val password = binding.password.text.toString().trim()
        binding.progressbar.visible(true)

        viewModel.login(email, password)
    }

    override fun getViewModel() = AuthViewModel::class.java
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(ApiInterFace::class.java), userPreferences)
}