package com.dicoding.yubi_apps.data.api.retrofit

import com.dicoding.yubi_apps.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    private const val BASE_URL = BuildConfig.BASE_URL

    fun getApiService(): ApiService {
        // Tambahkan interceptor untuk logging (opsional, bagus untuk debugging)
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        // OkHttpClient dengan logging interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        // Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Parsing JSON ke data class
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}