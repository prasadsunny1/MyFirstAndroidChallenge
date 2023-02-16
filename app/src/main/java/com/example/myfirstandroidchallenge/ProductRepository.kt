package com.example.myfirstandroidchallenge

import com.example.myfirstandroidchallenge.models.ProductDTO
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class ProductRepository
    (productService: ProductService) {

    private var _productService: ProductService = productService


    // Get products from service and return product or throw error
    fun getProducts(): ProductDTO {
        val response = _productService.getProducts().execute()
        if (response.errorBody() != null) {
            throw ProductFetchException("Failed To Fetch Products")
        } else {
            return response.body()!!
        }
    }

}

class ProductFetchException(message: String) : Exception(message)