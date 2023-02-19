package com.example.myfirstandroidchallenge.view_model

import com.example.myfirstandroidchallenge.data_sources.network.ProductItem


sealed class ProductLoadStates {
    object Loading : ProductLoadStates()
    data class Loaded(val products: List<ProductItem>) : ProductLoadStates()
    data class EmptyOrError(val message: String) : ProductLoadStates()
}