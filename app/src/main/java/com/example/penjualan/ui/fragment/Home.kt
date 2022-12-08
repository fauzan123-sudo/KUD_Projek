package com.example.penjualan.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.penjualan.R
import com.example.penjualan.adapter.MyAdapter
import com.example.penjualan.adapter.PictureAdapter
import com.example.penjualan.repository.Repository
import com.example.penjualan.ui.viewModel.MainViewModel
import com.example.penjualan.ui.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*


class Home : Fragment() {
    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }
    private val pictureAdapter by lazy { PictureAdapter(requireContext()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view:View =  inflater.inflate(R.layout.fragment_home, container, false)

        view.recV.adapter = pictureAdapter
        view.recV.layoutManager = GridLayoutManager(requireActivity(),2)
        view.recV.setHasFixedSize(true)

        val repository = Repository()
        val factory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.getData()
        viewModel.responData.observe(viewLifecycleOwner) { response->
            if (response.isSuccessful) {
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.message())
                view.grid_layout.visibility = View.GONE
                shimmer_layout.stopShimmer()
                shimmer_layout.visibility = View.GONE
                view.recV.visibility = View.VISIBLE
                response.body()?.let { pictureAdapter.setData(it) }
            }else{
                Toast.makeText(requireActivity(), response.code(), Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }

//        val carouselView = view.findViewById(R.id.carouselView) as CarouselView

    }
