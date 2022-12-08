package com.example.penjualan.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.example.penjualan.R
import com.example.penjualan.databinding.FragmentLoginBinding
import com.example.penjualan.model.Resource
import com.example.penjualan.network.ApiInterFace
import com.example.penjualan.repository.AuthRepository
import com.example.penjualan.repository.Repository
import com.example.penjualan.ui.viewModel.AuthViewModel
import com.example.penjualan.ui.viewModel.MainViewModel
import com.example.penjualan.ui.viewModel.MainViewModelFactory
import com.example.penjualan.util.DataStore
import com.example.penjualan.util.visible
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import kotlinx.coroutines.launch

//class LoginFragment: Fragment() {
//
//    lateinit var viewModel: MainViewModel
//    lateinit var dataStore: DataStore
//    var emailValue = ""
//    var passvalue = ""
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//
//        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
//
//        dataStore = DataStore(requireContext())
//
//
//        var txtEmail    = view.email.text.toString().trim()
//        var txtPassword = view.password.text.toString().trim()
//
//
//
//        view.progressbar.visibility = View.INVISIBLE
//        val repository = Repository()
//        val factory = MainViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
//
//
//        view.login.setOnClickListener {
//            if (txtEmail.isEmpty()){
//                email.error
//            }else if (txtPassword.isEmpty()){
//                password.error
//            }else if (txtEmail == "admin" && txtPassword == "admin"){
////                viewModel.loginRequest(txtEmail, txtPassword)
//            }else{
//                Toast.makeText(requireContext(), "email atau password anda salah", Toast.LENGTH_SHORT).show()
//            }
//        }
//        return view
//    }
//
//}

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            binding.progressbar.visible(false)
            viewModel.loginResponse.observe(viewLifecycleOwner) {
                binding.progressbar.visible(false)
                when (it) {
                    is Resource.Success -> {

                        Toast.makeText(requireContext(), "berhasil login", Toast.LENGTH_SHORT).show()
                        requireActivity().onBackPressed()
//                        Navigation.findNavController(requireView()).popBackStack()
//                            viewModel.saveAuthToken(it.value.user.access_token)
//                            requireActivity().startNewActivity(MainActivity::class.java)
                    }
                    is Resource.Failure -> {
                        Toast.makeText(requireContext(), "login Failure", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            binding.login.setOnClickListener {
                val email    = binding.email.text.toString().trim()
                val password = binding.password.text.toString().trim()
                binding.progressbar.visible(true)

                viewModel.login(email, password)
            }
        }

        override fun getViewModel() = AuthViewModel::class.java
        override fun getFragmentBinding(
            inflater: LayoutInflater,
            container: ViewGroup?
        )= FragmentLoginBinding.inflate(inflater, container, false)

        override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(ApiInterFace::class.java), userPreferences)
}