package com.example.penjualan.network

import com.example.penjualan.model.kategori.Kategori
import com.example.penjualan.network.response.DataObat
import com.example.penjualan.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterFace {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>

    @GET("search-obat")
    suspend fun cariBarang(
        @Query("q") q: String
    ):DataObat

    @GET("search-obat")
    suspend fun searchAll(
        @Query("q") q: String?,
        @Query("")  k: Int?
    ):DataObat

    @GET("search-obat")
    suspend fun searchByCategory(
        @Query("k") k: String
    ):DataObat

    @GET("obat")
    suspend fun getObat(): DataObat

    @GET("kategori-obat")
    suspend fun kategori(): Kategori
}