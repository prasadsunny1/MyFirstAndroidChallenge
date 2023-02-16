package com.example.myfirstandroidchallenge

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    /**
     * A state holder for loading, loaded, error and empty states
     */
    private val _productLoadStates = MutableLiveData<ProductLoadStates>()

    // expose an immutable live data
    val productLoadStates: LiveData<ProductLoadStates> = _productLoadStates

    /// When view is ready, get products
    fun onViewCreated() {
        getProducts()
    }

    /// Get products from service
    private fun getProducts() {
        _productLoadStates.postValue(ProductLoadStates.Loading)
        viewModelScope.launch(context = Dispatchers.IO) {
            val productItems =
                productRepository.getAllProductsWithReFetchIfNeeded(forceInvalidateCatchAndReFetch = false)
            if (!productItems.isNullOrEmpty()) {
                _productLoadStates.postValue(ProductLoadStates.Loaded(productItems))
            } else {
                _productLoadStates.postValue(ProductLoadStates.EmptyOrError("Failed to load or products not found"))
            }
        }
    }

}