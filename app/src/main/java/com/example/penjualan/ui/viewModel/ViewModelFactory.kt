package com.example.penjualan.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.penjualan.repository.AuthRepository
import com.example.penjualan.repository.BaseRepository
import com.example.penjualan.repository.Repository
import java.lang.IllegalArgumentException

class ViewModelFactory ( private val repository:BaseRepository ):ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass  Not Found")
        }
    }

}
