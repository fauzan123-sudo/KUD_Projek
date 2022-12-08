package com.example.penjualan.util

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore (context: Context){
    private val dataStore = context.createDataStore(name = "user_prefs")

    companion object {
        val EMAIL = preferencesKey<String>("email")
        val PASSWORD = preferencesKey<String>("password")
    }

//    save user email and password
    suspend fun saveUser(email:String, passsword:String){
        dataStore.edit {
            it[EMAIL] = email
            it[PASSWORD] = passsword
        }

    }


//    get email user
    val emailFlow: Flow<String> = dataStore.data.map {
        it[EMAIL] ?: ""
    }

//    get password
    val passwordFlow: Flow<String> = dataStore.data.map {
        it[EMAIL] ?: ""
    }



}