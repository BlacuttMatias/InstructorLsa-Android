package com.example.instructorlsa.services

import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val token = "Token " + InstructorLsaConfig.getUserToken()

    fun getRetrofitInstance(baseUrl: String = "http://instructorlsa.herokuapp.com"): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitMockInstance(baseUrl: String = "https://demo8670899.mockable.io"): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getHeaders(): HashMap<String, String>{
        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = token
        return headers
    }

    fun getHeadersWithMultipart(): HashMap<String, String>{
        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"
        headers["Authorization"] = token
        return headers
    }
}