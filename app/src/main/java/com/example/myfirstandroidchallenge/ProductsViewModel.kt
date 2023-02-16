package com.example.myfirstandroidchallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstandroidchallenge.models.ProductItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel() : ViewModel() {


    //    var products: ProductDTO? = null
    private var productsLiveData = MutableLiveData<List<ProductItem>>()
    private var productRepository: ProductRepository


    init {
        val productService = ProductService.create()
        productRepository = ProductRepository(productService)
    }

    /// Get products from service
     fun getProducts() {
        viewModelScope.launch(context = Dispatchers.IO) {
            val data = productRepository.getProducts()
            if (!data.data?.productItems.isNullOrEmpty()) {
                productsLiveData.postValue(data.data?.productItems!!)
            }
        }
    }

    fun observeProductLiveData(): LiveData<List<ProductItem>> {
        return productsLiveData
    }
}