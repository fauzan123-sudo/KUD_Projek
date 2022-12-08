package com.example.penjualan.network

import com.example.penjualan.network.response.LoginResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser():LoginResponse
}