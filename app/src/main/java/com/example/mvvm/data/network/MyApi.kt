package com.example.mvvm.data.network

import android.telecom.Call
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {


    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("username") userId: String,
        @Field("password") password: String,
        @Field("grant_type") grant_type: String

    ): retrofit2.Call<ResponseBody>

    companion object {
        operator fun invoke(): MyApi{
            return Retrofit.Builder()
                .baseUrl("https://exlmobility-uat.exlservice.com/OneEXL/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}