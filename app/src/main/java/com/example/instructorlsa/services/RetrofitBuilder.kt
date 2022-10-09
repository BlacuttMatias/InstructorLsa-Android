package com.example.instructorlsa.services

import com.example.instructorlsa.viewmodels.InstructorLsaConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    val httpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun getToken(): String{
        return "Token " + InstructorLsaConfig.getUserToken()
    }

    fun getRetrofitInstance(baseUrl: String = "http://instructorlsa.herokuapp.com"): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    fun getRetrofitMockInstance(baseUrl: String = "https://demo8670899.mockable.io"): Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    fun getHeaders(): HashMap<String, String>{
        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"
        headers["Content-Type"] = "application/json"
        headers["Authorization"] = getToken()
        return headers
    }

    fun getHeadersWithMultipart(): HashMap<String, String>{
        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"
        headers["Authorization"] = getToken()
        return headers
    }
}