package com.example.myfirstandroidchallenge.view_model

import androidx.lifecycle.*
import com.example.myfirstandroidchallenge.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val coroutineScope: CoroutineDispatcher
) :
    ViewModel() {


    private val _productLoadStates = MutableLiveData<ProductLoadStates>()

    /**
     * A state holder for loading, loaded, error and empty states
     */
    val productLoadStates: LiveData<ProductLoadStates> = _productLoadStates

    /**
     * When view is ready, load products
     */
    fun onViewCreated() {
        getProducts()
    }

    /// Get products from service
    private fun getProducts(userInitiateRefresh: Boolean = false, searchKeyword: String? = null) {
        _productLoadStates.postValue(ProductLoadStates.Loading)
        viewModelScope.launch(context = coroutineScope) {
            val productItems =
                productRepository.getAllProductsWithReFetchIfNeeded(
                    forceInvalidateCatchAndReFetch = userInitiateRefresh,
                    searchKeyword = searchKeyword
                )
            if (!productItems.isNullOrEmpty()) {
                _productLoadStates.postValue(ProductLoadStates.Loaded(productItems))

            } else {
                _productLoadStates.postValue(ProductLoadStates.EmptyOrError("Failed to load or products not found"))
            }
        }
    }

    fun refreshProducts() {
        getProducts(true)
    }

    /**
     * Search products
     * If $searchKeyword is null, then it will search for all products
     */
    fun searchProducts(searchKeyword: String? = null) {
        getProducts(false, searchKeyword)
    }

}