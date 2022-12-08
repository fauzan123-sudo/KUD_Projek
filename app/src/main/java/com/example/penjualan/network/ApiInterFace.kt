package com.example.penjualan.network

import com.example.penjualan.network.response.LoginResponse
import com.example.penjualan.network.response.PicsResponse
import com.example.penjualan.network.response.Post
import com.example.penjualan.network.response.RequestBodies
import retrofit2.Response
import retrofit2.http.*

interface ApiInterFace {

    @GET("v2/list")
    suspend fun getPictures(): Response<PicsResponse>

    @GET("posts")
    suspend fun getPost(): Response<List<Post>>

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email:String,
        @Field("password") password:String
    ): LoginResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email:String,
        @Field("password") password:String
    ): Response<LoginResponse>

    @GET("")
    suspend fun cariBarang(
        @Query("")  barang:String,
        @Query("")  harga:Int
    ) :Response<List<Post>>


}