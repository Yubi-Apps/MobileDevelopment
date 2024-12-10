package com.dicoding.yubi_apps.data.repository

import com.dicoding.yubi_apps.Response
import com.dicoding.yubi_apps.data.api.retrofit.ApiService
import okhttp3.MultipartBody


open class UploadRepository(
    private val apiService: ApiService
) {
    suspend fun uploadImage(imageFile: MultipartBody.Part): Response {
        return apiService.uploadImage(imageFile)
    }

}