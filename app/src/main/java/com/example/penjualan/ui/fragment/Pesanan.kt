package com.example.penjualan.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.penjualan.R
import com.example.penjualan.UserPreferences


class Pesanan : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_pesanan, container, false)
        val userPreferences = UserPreferences(requireContext())
        userPreferences.authToken.asLiveData().observe(viewLifecycleOwner) {
            if (it == null) findNavController().navigate(PesananDirections.actionPesananToLoginFragment())
                else Toast.makeText(requireContext(), "tidak kosong", Toast.LENGTH_SHORT).show()
        }

//        val action = PesananDirections.actionPesananToLoginFragment()
//        findNavController().navigate(action)
        return view
    }


}