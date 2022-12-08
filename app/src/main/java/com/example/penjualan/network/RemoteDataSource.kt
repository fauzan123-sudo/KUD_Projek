package com.example.penjualan.network

import com.example.penjualan.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {

    companion object {
        const val BASE_URL = "http://192.168.1.2/kud_shop/api/"
    }
//        operator fun invoke(
//            networkConnectionInterceptor: NetworkConnectionInterceptor
//        ): ApiInterFace {
//
//            val okkHttpclient = OkHttpClient.Builder()
//                .addInterceptor(networkConnectionInterceptor)
//                .build()
//
//            return Retrofit.Builder()
//                .client(okkHttpclient)
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ApiInterFace::class.java)
//
//        }

        fun <Api> buildApi(
            api: Class<Api>,
            authToken:String?= null
        ): Api {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder().addInterceptor{ chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.addHeader("Authorization","Bearer $authToken")}
                            .build())
                            }.also { client->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                    }
                }.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(api)
        }

//    private fun buildTokenApi(): TokenRefreshApi {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(getRetrofitClient())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(TokenRefreshApi::class.java)
//    }
//
//    private fun getRetrofitClient(authenticator: Authenticator? = null): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                chain.proceed(chain.request().newBuilder().also {
//                    it.addHeader("Accept", "application/json")
//                }.build())
//            }.also { client ->
//                authenticator?.let { client.authenticator(it) }
//                if (BuildConfig.DEBUG) {
//                    val logging = HttpLoggingInterceptor()
//                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//                    client.addInterceptor(logging)
//                }
//            }.build()



}