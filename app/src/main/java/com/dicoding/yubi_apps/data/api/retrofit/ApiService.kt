package com.dicoding.yubi_apps.data.api.retrofit

import com.dicoding.yubi_apps.Response
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("getPredictionOutput")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part
    ): Response
}