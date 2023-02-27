package com.example.myfirstandroidchallenge.data.api.service

import com.example.myfirstandroidchallenge.data.api.config.ProductApiConfig
import com.example.myfirstandroidchallenge.data.api.dto.ProductResponseDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET

interface ProductAPIService {

    companion object Factory {

        /**
         * Create a retrofit instance for ProductAPIService
         * It uses JacksonConverterFactory to convert JSON to Kotlin objects
         */
        fun create(retrofit: Retrofit): ProductAPIService {
            return retrofit.create(ProductAPIService::class.java)
        }
    }

    /**
     * Get products from API endpoint
     */
    @GET(ProductApiConfig.GET_PRODUCTS_ENDPOINT)
    fun getProducts(): Call<ProductResponseDTO>
}