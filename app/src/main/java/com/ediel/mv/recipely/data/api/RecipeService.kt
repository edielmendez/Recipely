package com.ediel.mv.recipely.data.api

import com.ediel.mv.recipely.data.response.RecipesResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RecipeService {
    @GET("/recipes")
    suspend fun getRecipes(): Response<RecipesResponse>

    companion object {
        private const val BASE_URL = "https://demo0412169.mockable.io/"

        fun create(): RecipeService {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RecipeService::class.java)
        }
    }
}