package com.example.penjualan.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.penjualan.model.Resource
import com.example.penjualan.network.response.akun.User
import com.example.penjualan.repository.UserRepository
import kotlinx.coroutines.launch

class AkunViewModel(private val repository:UserRepository):BaseViewModel(repository) {
    private val _user:MutableLiveData<Resource<User>> = MutableLiveData()
    val user:LiveData<Resource<User>>
    get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()
    }
}