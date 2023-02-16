package com.example.myfirstandroidchallenge

import com.example.myfirstandroidchallenge.models.ProductDTO
import javax.inject.Inject

class ProductRepository
@Inject constructor(private val productService: ProductService) {

    // Get products from service and return product or throw error
    fun getProducts(): ProductDTO? {
        val response = productService.getProducts().execute()
        if (response.errorBody() != null) {
            return null
        } else {
            return response.body()!!
        }
    }
}

class ProductFetchException(message: String) : Exception(message)