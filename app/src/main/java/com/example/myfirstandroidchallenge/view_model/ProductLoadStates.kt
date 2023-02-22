package com.example.myfirstandroidchallenge.view_model

import com.example.myfirstandroidchallenge.repository.ProductDO


sealed class ProductLoadStates {
    object Loading : ProductLoadStates()
    data class Loaded(val products: List<ProductDO>) : ProductLoadStates()
    data class EmptyOrError(val message: String) : ProductLoadStates()
}