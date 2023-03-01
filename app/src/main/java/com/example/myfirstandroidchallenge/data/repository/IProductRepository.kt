package com.example.myfirstandroidchallenge.data.repository

import com.example.myfirstandroidchallenge.data.databse.configs.ProductDbConfig.CACHE_EXPIRY_TIME
import com.example.myfirstandroidchallenge.model.ProductDO

/**
 * This interface is used to define the repository methods for the product table
 */
interface IProductRepository {

    suspend fun getAllProductsWithReFetchIfNeeded(
        forceInvalidateCatchAndReFetch: Boolean = false,
        cacheExpiryTimeInMills: Int? = CACHE_EXPIRY_TIME,
        searchKeyword: String? = null,
    ): List<ProductDO>
}