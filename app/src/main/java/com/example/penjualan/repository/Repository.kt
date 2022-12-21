package com.example.penjualan.repository

import com.example.penjualan.network.RetrofitInstance
import retrofit2.Response

class Repository() {

//        suspend fun getPictures() = RetrofitInstance.picsumApi.getPictures()
//
//        suspend fun getData() : Response<PicsResponse> {
//                return RetrofitInstance.picsumApi.getPictures()
//        }
//
//        suspend fun pushPost(): Response<List<Post>> {
//                return RetrofitInstance.api.getPost()
//        }

        suspend fun loginUser(email:String,password:String) = RetrofitInstance.api.loginUser(email,password)



//        suspend fun saveUser(token:String){
//                preferences.saveAuthToken(token)
//        }
//
//        suspend fun readFromDataStore(){
//                preferences.readFromDataStore.asLiveData()
//        }







//        suspend fun saveUser(user: User) = db.getUserDao().upsert(user)
//
//        fun getUser() = db.getUserDao().getuser()

//    suspend fun userLogin(email: String, password: String): AuthResponse {
//        return apiRequest { api.userLogin(email, password) }
//    }

//    suspend fun login(
//        email: String,
//        password: String
//    ) = safeApiCall {
//        api.login(email, password)
//    }


//    suspend fun dataPegawaiPersonal(userId: Int): Response<List<ModelDataPegawai>> {
//        return ApiInstance.api.dataPegawaiPersonal(userId)
//    }
//
//    suspend fun login(username:String, password:String): Response<loginResponse> {
//        return ApiInstance.api.login(username, password)
//    }


}