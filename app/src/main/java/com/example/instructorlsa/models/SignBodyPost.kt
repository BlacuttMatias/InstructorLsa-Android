package com.example.instructorlsa.models

import com.google.gson.annotations.SerializedName

data class SignBodyPost(
    @SerializedName("idSign") val signId: Int
)
