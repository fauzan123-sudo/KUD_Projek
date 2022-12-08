package com.example.penjualan.network.response

data class LoginResponse(
    val access_token: String,
    val message: String,
    val token_type: String,
    val user: User
)