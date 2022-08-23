package com.example.mvvmwithcoroutine.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitHelper {

    const val BASE_URL = "https://quotable.io/"

    fun getRetrofit () : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}