package com.example.penjualan.repository

import com.example.penjualan.UserPreferences
import com.example.penjualan.network.ApiInterFace

class AuthRepository(
    private val api: ApiInterFace,
    private val preferences:UserPreferences
) : BaseRepository() {

    suspend fun login( email: String, password: String ) = safeApiCall { api.login(email, password) }

    suspend fun loginUser( email: String, password: String ) = safeApiCall { api.login(email, password) }

    suspend fun savedAuthToken(token:String){
        preferences.saveAuthToken(token)
    }


}