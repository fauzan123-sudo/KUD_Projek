package com.example.penjualan.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.penjualan.repository.*
import com.example.penjualan.ui.fragment.DetailProduct
import java.lang.IllegalArgumentException

class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(AkunViewModel::class.java) -> AkunViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(SavedViewModel::class.java) -> SavedViewModel(repository as SavedRepository) as T
            modelClass.isAssignableFrom(DataObatViewModel::class.java) -> DataObatViewModel(
                repository as DataObatRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass  Not Found")
        }
    }

}
