package com.example.penjualan.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.penjualan.repository.Repository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModelClass  Not Found")
        }
    }
}