package com.example.myfirstandroidchallenge

import com.example.myfirstandroidchallenge.models.ProductItem


sealed class ProductLoadStates {
    object Loading : ProductLoadStates()
    data class Loaded(val products: List<ProductItem>) : ProductLoadStates()
    data class EmptyOrError(val message: String) : ProductLoadStates()
}