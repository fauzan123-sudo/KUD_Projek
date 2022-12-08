package com.example.penjualan.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.penjualan.R
import com.example.penjualan.UserPreferences
import com.example.penjualan.repository.Repository
import com.example.penjualan.ui.viewModel.MainViewModel
import com.example.penjualan.ui.viewModel.ViewModelFactory
import com.example.penjualan.util.SessionManager
import kotlinx.android.synthetic.main.fragment_akun.*

class Akun : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view = inflater.inflate(R.layout.fragment_akun, container, false)

        val userPrefernces = UserPreferences(requireContext())

        userPrefernces.authToken.asLiveData().observe(viewLifecycleOwner) {

            Toast.makeText(requireContext(), "doesnt token exist", Toast.LENGTH_SHORT).show()
            val action = AkunDirections.actionAkunToLoginFragment()
            findNavController().navigate(action)
        }



//        val token = SessionManager.getToken(requireActivity())
//        if (!token.isNullOrBlank()) {
//
//        }else{
//            val action = AkunDirections.actionAkunToLoginFragment()
//            findNavController().navigate(action)
//            Toast.makeText(requireActivity(), "empty token", Toast.LENGTH_SHORT).show()
//        }



//        val viewModelFactory = ViewModelFactory(repository)
//
//        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
//        viewModel.dataPegawaiPersonal(6)
//        viewModel.myCustomPosts.observe(viewLifecycleOwner) { response ->
//            if (response.isSuccessful) {
//                Log.i("TAG", ""+ response.body()?.toString())
//                nama.text = response.body()?.toString()
//            } else {
//                nama.text = response.body()?.toString()
//            }
//        }


        return view
    }

    private fun checkToken() {

    }

}