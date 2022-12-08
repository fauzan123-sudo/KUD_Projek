package com.example.penjualan.network.response

object RequestBodies {

    data class LoginBody(
        val email:String,
        val password:String
    )

}