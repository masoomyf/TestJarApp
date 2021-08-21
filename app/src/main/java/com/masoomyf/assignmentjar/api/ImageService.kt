package com.masoomyf.assignmentjar.api

import com.masoomyf.assignmentjar.BuildConfig
import com.masoomyf.assignmentjar.data.UnsplashResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("search/photos")
    suspend fun getPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_ACCESS_KEY
    ): UnsplashResponse

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"

        fun create(): ImageService {

            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImageService::class.java)
        }
    }
}