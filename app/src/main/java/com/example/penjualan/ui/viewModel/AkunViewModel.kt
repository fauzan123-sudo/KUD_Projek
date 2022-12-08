package com.example.penjualan.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.penjualan.model.Resource
import com.example.penjualan.network.response.LoginResponse
import com.example.penjualan.repository.UserRepository
import kotlinx.coroutines.launch

class AkunViewModel(private val repository:UserRepository):ViewModel() {
    private val _user:MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user:LiveData<Resource<LoginResponse>>
    get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = repository.getUser()
    }
}