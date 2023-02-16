package com.example.myfirstandroidchallenge

import com.example.myfirstandroidchallenge.models.ProductDTO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET

interface ProductService {

    companion object Factory {
        fun create(): ProductService {
            val retrofit = Retrofit.Builder().addConverterFactory(
                JacksonConverterFactory.create()
            ).baseUrl("https://run.mocky.io/v3/").build()
            return retrofit.create(ProductService::class.java)
        }
    }

    /// Get products from service
    @GET("995ce2a0-1daf-4993-915f-8c198f3f752c")
    fun getProducts(): Call<ProductDTO>
}