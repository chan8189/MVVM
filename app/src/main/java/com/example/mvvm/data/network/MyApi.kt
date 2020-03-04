package com.example.mvvm.data.network

import com.example.mvvm.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface MyApi {


    @FormUrlEncoded

    @POST("login")
    fun userLogin(
        @Field("username") userId: String,
        @Field("password") password: String,
        @Field("grant_type") grant_type: String

    ): retrofit2.Call<ResponseBody>

    companion object {
        private const val CONNECTION_TIMEOUT: Long = 30
        private const val NETWORK_CALL_TIMEOUT: Long = 30

        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .baseUrl("https://exlmobility-uat.exlservice.com/OneEXL/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(provideHeaderInterceptor())
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = if (BuildConfig.DEBUG)
                            HttpLoggingInterceptor.Level.BODY
                        else
                            HttpLoggingInterceptor.Level.NONE
                    })
            .connectTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_CALL_TIMEOUT, TimeUnit.SECONDS)
            .build()

        private fun provideHeaderInterceptor(): Interceptor {
            return Interceptor { chain: Interceptor.Chain ->
                val accessToken = ""
                val request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer $accessToken").build()
                chain.proceed(request)
            }
        }


    }
}