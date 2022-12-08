package com.example.penjualan.repository

import com.example.penjualan.UserPreferences
import com.example.penjualan.network.ApiInterFace
import com.example.penjualan.network.UserApi

class UserRepository(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }



}