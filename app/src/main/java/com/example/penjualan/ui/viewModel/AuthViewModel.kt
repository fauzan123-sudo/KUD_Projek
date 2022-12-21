package com.example.penjualan.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.penjualan.model.Resource
import com.example.penjualan.network.response.LoginResponse
import com.example.penjualan.repository.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel ( private val repository:AuthRepository ) :BaseViewModel(repository) {

    private val _loginResponse :MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse:LiveData<Resource<LoginResponse>>
    get() = _loginResponse

    fun login(email: String, password: String) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email, password)
    }

    suspend fun saveAuthToken(token:String) {
        repository.savedAuthToken(token)
    }

}