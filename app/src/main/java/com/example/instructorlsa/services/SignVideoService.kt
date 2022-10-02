package com.example.instructorlsa.services

import android.os.FileUtils
import com.example.instructorlsa.models.ResponseCheckSignVideo
import com.example.instructorlsa.models.SignBodyPost
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import java.io.File
import java.nio.file.Files

interface SignVideoApiService{
    @Multipart
    @POST("signVideo/")
    suspend fun checkSignVideo(
        @HeaderMap headers: Map<String, String>,
        @Part("idSena") idSign: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<ResponseCheckSignVideo>
}

class SignVideoService {
    suspend fun checkSignVideo(idSign: String, videoFile: File): Response<ResponseCheckSignVideo> {
        val mediaTypeVideo = MediaType.parse("video/mp4")//okhttp3.MultipartBody.FORM
        val requestBodyVideoFile = RequestBody.create(mediaTypeVideo, videoFile)
        val multipartVideoBody = MultipartBody.Part.createFormData("file", videoFile.name, requestBodyVideoFile)
        val requestBodyIdSign = RequestBody.create(MediaType.parse("text/plain"), idSign)
        return RetrofitBuilder.getRetrofitInstance()
            .create(SignVideoApiService::class.java)
            .checkSignVideo(headers = RetrofitBuilder.getHeadersWithMultipart(), idSign = requestBodyIdSign, file = multipartVideoBody)
    }
}