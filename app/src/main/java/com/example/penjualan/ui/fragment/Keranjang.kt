package com.example.penjualan.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.penjualan.R
import com.example.penjualan.UserPreferences
import com.example.penjualan.adapter.AdapterSaved
import com.example.penjualan.model.saved.Data
import io.paperdb.Paper
import kotlinx.android.synthetic.main.fragment_keranjang.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Keranjang : Fragment() {

    private lateinit var data:ArrayList<Data>
    private val adapter by lazy {
        AdapterSaved(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view:View = inflater.inflate(R.layout.fragment_keranjang, container, false)
        Paper.init(requireContext())

        CoroutineScope(Dispatchers.Main).launch {

            data = Paper.book().read("notes",ArrayList()) ?: ArrayList()
            Log.d("paper", "$data")

            let { adapter.setDatas(data) }

            view.recKeranjang.adapter =adapter

            view.recKeranjang.layoutManager = LinearLayoutManager(requireActivity())
            view.recKeranjang.setHasFixedSize(true)

        }

        return view
    }

}


