package com.example.penjualan

import android.app.Application
import io.paperdb.Paper

class PenjualanApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        Paper.init(this)
    }
}