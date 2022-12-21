package com.example.penjualan.network

import com.example.penjualan.network.response.akun.User
import okhttp3.ResponseBody
import retrofit2.http.GET

interface UserApi {

    @GET("profile")
    suspend fun getUser():User

    @GET("logout")
    suspend fun logOut():ResponseBody
}