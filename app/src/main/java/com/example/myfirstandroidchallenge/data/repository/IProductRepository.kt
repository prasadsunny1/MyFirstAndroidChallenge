package com.example.myfirstandroidchallenge.data.repository

import com.example.myfirstandroidchallenge.data.databse.configs.ProductDbConfig.CACHE_EXPIRY_TIME
import com.example.myfirstandroidchallenge.model.ProductDO

interface IProductRepository {

    suspend fun getAllProductsWithReFetchIfNeeded(
        forceInvalidateCatchAndReFetch: Boolean = false,
        cacheExpiryTimeInMills: Int? = CACHE_EXPIRY_TIME,
        searchKeyword: String? = null,
    ): List<ProductDO>
}