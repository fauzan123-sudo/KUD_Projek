package com.example.penjualan.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.penjualan.R
import com.example.penjualan.UserPreferences

class Beranda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)



        val userPreferences=UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this) {
            if (it == null)
                Toast.makeText(this, "kosong", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(this, "tidak kosong", Toast.LENGTH_SHORT).show()
        }
    }
}