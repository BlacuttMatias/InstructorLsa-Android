package com.example.instructorlsa.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    fun getRetrofitInstance(): Retrofit{
        val baseUrl = "https://demo8670899.mockable.io/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}