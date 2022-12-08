package com.example.penjualan.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.penjualan.R
import com.example.penjualan.adapter.MyAdapter
import com.example.penjualan.repository.Repository
import com.example.penjualan.ui.viewModel.MainViewModel
import com.example.penjualan.ui.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupRecyclerview()

        val repository = Repository()
        val factory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.getPost()
        viewModel.myCustomPosts.observe(this) { response ->
            if (response.isSuccessful) {
                Log.d("Main", response.body().toString())
                Log.d("Main", response.code().toString())
                Log.d("Main", response.message())
                response.body()?.let { myAdapter.setData(it) }
                var datas = response.body()?.let { myAdapter.setData(it) }
                Toast.makeText(this, datas.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerview() {
        recylerView.adapter = myAdapter
        recylerView.layoutManager = LinearLayoutManager(this)
    }
}