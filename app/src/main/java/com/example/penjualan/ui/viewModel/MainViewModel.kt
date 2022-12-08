package com.example.penjualan.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.penjualan.network.response.LoginResponse
import com.example.penjualan.network.response.PicsResponse
import com.example.penjualan.network.response.PicsResponseItem
import com.example.penjualan.network.response.Post
import com.example.penjualan.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val responData:MutableLiveData<Response<PicsResponse>>       = MutableLiveData()
    var myCustomPosts: MutableLiveData<Response<List<Post>>>     = MutableLiveData()
    val loginResponse:MutableLiveData<Response<LoginResponse>>   = MutableLiveData()


    fun getData(){
        viewModelScope.launch {
            responData.value = repository.getData()
        }
    }

    fun getPost(){
        viewModelScope.launch {
            val response = repository.pushPost()
            myCustomPosts.value = response
        }
    }

//    fun save(email:String,password:String){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.savedAuthToken(email,password)
//        }
//    }
//
//    fun getUsernameAndPassword(){
//        viewModelScope.launch {
//            repository.readFromDataStore()
//        }
//    }







}
