package com.example.myfirstandroidchallenge

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    /**
     * A state holder for loading, loaded, error and empty states
     */
    private val _productLoadStates = MutableLiveData<ProductLoadStates>()
    val productLoadStates = _productLoadStates

    /// When view is ready, get products
    fun onViewCreated() {
        getProducts()
    }

    /// Get products from service
    private fun getProducts() {
        productLoadStates.postValue(ProductLoadStates.Loading)
        viewModelScope.launch(context = Dispatchers.IO) {
            val data = productRepository.getProducts()
            if (!data.data?.productItems.isNullOrEmpty()) {
                productLoadStates.postValue(ProductLoadStates.Loaded(data.data?.productItems!!))
            } else {
                productLoadStates.postValue(ProductLoadStates.EmptyOrError("Failed to load or products not found"))
            }
        }
    }

}