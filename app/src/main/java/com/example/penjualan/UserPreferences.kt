package com.example.penjualan

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


//import androidx.datastore.preferences.core.Preferences

class UserPreferences(context:Context) {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(name = "my_data_store")
    }

    val authToken: Flow<String?> get() = dataStore.data.map { preferences ->
        preferences[KEY_AUTH]
    }

    suspend fun saveAuthToken(authToken: String){
        dataStore.edit{ preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    suspend fun getFromDataStore() = dataStore.data.map {preferences->
        preferences[EMAIL]
        preferences[PASSWORD]
    }

    val readFromDataStore: Flow<String> = dataStore.data
        .catch { exception ->
            if(exception is IOException){
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            }else {
                throw exception
            }
        }
        .map { preference ->
            val myName = preference[EMAIL] ?: "none"
            myName
        }

    companion object{
        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val EMAIL = preferencesKey<String>("email")
        private val PASSWORD = preferencesKey<String>("password")
    }

    suspend fun savedLoginUser (email: String,password:String){
        dataStore.edit { preferences->
            preferences[EMAIL] = email
            preferences[PASSWORD] = password

        }
    }
}