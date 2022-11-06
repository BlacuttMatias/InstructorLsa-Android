package com.lsadomain.instructorlsa.models

data class ResponseCheckSignVideo(
    val validation: String,
    val response: String,
    val prediction: String?
)