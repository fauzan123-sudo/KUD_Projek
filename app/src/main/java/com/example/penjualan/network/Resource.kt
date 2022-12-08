package com.example.penjualan.network

import okhttp3.ResponseBody

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}

//sealed class Resource<out T> {
//    data class Success<out T>(val value: T) : Resource<T>()
//    data class Failure(
//        val isNetworkError: Boolean,
//        val errorCode: Int?,
//        val errorBody: ResponseBody?
//    ) : Resource<Nothing>()
//    object Loading : Resource<Nothing>()
//}
