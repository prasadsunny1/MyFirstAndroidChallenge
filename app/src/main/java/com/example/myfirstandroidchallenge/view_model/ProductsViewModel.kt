package com.example.myfirstandroidchallenge.view_model

import android.os.Handler
import androidx.lifecycle.*
import com.example.myfirstandroidchallenge.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository, private val coroutineScope: CoroutineDispatcher
) : ViewModel() {

    private val _productLoadStates = MutableLiveData<ProductLoadStates>()

    /**
     * A state holder for loading, loaded, error and empty states
     */
    val productLoadStates: LiveData<ProductLoadStates> = _productLoadStates

    /**
     * A timer to delay search operation
     */
    private val searchTimer = Timer()

    /**
     * A timer task to schedule search operation
     */
    private var searchTimerTask: TimerTask? = null

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
            val productItems = productRepository.getAllProductsWithReFetchIfNeeded(
                forceInvalidateCatchAndReFetch = userInitiateRefresh, searchKeyword = searchKeyword
            )
            if (productItems.isEmpty().not()) {
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

        searchTimerTask?.cancel()

        // Schedule a delayed search operation
        searchTimerTask = object : TimerTask() {
            override fun run() {
                getProducts(false, searchKeyword)
            }
        }
        searchTimer.schedule(searchTimerTask, 500L)
    }
}