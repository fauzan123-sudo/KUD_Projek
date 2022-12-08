package com.example.penjualan.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.penjualan.R
import com.example.penjualan.util.SessionManager

class Keranjang : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view:View = inflater.inflate(R.layout.fragment_keranjang, container, false)

        val token = SessionManager.getToken(requireActivity())
        if (!token.isNullOrBlank()) {
            val action = KeranjangDirections.actionKeranjangToLoginFragment()
            findNavController().navigate(action)
        }

        return view
    }

}