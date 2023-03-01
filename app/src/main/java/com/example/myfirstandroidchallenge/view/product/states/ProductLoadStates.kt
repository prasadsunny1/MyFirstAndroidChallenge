package com.example.myfirstandroidchallenge.view.product.states

import com.example.myfirstandroidchallenge.model.ProductDO

/**
 * This sealed class is used to define the states of the product list
 */
sealed class ProductLoadStates {

    object Loading : ProductLoadStates()
    data class Loaded(val products: List<ProductDO>) : ProductLoadStates()
    data class EmptyOrError(val message: String) : ProductLoadStates()
}