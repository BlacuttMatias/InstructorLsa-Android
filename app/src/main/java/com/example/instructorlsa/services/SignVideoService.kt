package com.example.instructorlsa.services

import android.os.FileUtils
import android.util.Log
import com.example.instructorlsa.models.Category
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
    @POST("send_video")
    suspend fun checkSignVideo(
        @HeaderMap headers: Map<String, String>,
        @Part("position") position: RequestBody,
        @Part("web") web: RequestBody,
        @Part("category") category: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<ResponseCheckSignVideo>
}

class SignVideoService {
    suspend fun checkSignVideo(idSign: String, position: String, category: String, videoFile: File): Response<ResponseCheckSignVideo> {
        val mediaTypeVideo = MediaType.parse("video/mp4")//MultipartBody.FORM
        val requestBodyVideoFile = RequestBody.create(mediaTypeVideo, videoFile)
        val multipartVideoBody = MultipartBody.Part.createFormData("video", videoFile.name, requestBodyVideoFile)
        val requestBodyIdSign = RequestBody.create(MediaType.parse("text/plain"), idSign)
        val requestBodyPositionSign = RequestBody.create(MediaType.parse("text/plain"), position)
        val requestBodyCategorySign = RequestBody.create(MediaType.parse("text/plain"), category)
        val requestBodyIsWeb = RequestBody.create(MediaType.parse("text/plain"), "false")
        return RetrofitBuilder.getRetrofitInstance("https://instructor-lsa.azurewebsites.net")
            .create(SignVideoApiService::class.java)
            .checkSignVideo(headers = RetrofitBuilder.getHeadersWithMultipart(),
                web = requestBodyIsWeb,
                position = requestBodyPositionSign,
                category = requestBodyCategorySign,
                file = multipartVideoBody)
    }
}