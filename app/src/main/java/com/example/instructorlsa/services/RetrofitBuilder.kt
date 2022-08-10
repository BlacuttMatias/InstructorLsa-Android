package com.example.instructorlsa.services

import com.example.instructorlsa.viewmodels.InstructorLsaConfig
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

    fun getHeaders(): HashMap<String, String>{
        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"
        headers["Content-Type"] = "application/json"
        headers["token"] = InstructorLsaConfig.getUserToken()
        headers["email"] = InstructorLsaConfig.getUserEmail()
        return headers
    }
}