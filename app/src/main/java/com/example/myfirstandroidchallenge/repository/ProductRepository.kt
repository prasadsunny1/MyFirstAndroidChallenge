package com.example.myfirstandroidchallenge.repository

import com.example.myfirstandroidchallenge.AppConstants
import com.example.myfirstandroidchallenge.data_sources.database.ProductDatabase
import com.example.myfirstandroidchallenge.data_sources.database.ProductEntity
import com.example.myfirstandroidchallenge.data_sources.database.ProductEntityColumnNames
import com.example.myfirstandroidchallenge.data_sources.network.ProductAPIService
import com.example.myfirstandroidchallenge.data_sources.network.ProductItem
import javax.inject.Inject

class ProductRepository
@Inject constructor(
    private val productAPIService: ProductAPIService,
    private val productDatabaseService: ProductDatabase,
) {

    // Get products from service and return product or throw error
    private fun getProductsFromApiAndCache(): List<ProductItem>? {

        val response = productAPIService.getProducts().execute()
        if (response.errorBody() != null) {
            return null
        } else {
            saveProductsToDB(response.body()?.data?.productItems)
            return response.body()?.data?.productItems
        }
    }

    private fun saveProductsToDB(products: List<ProductItem>?) {
        val productEntities: List<ProductEntity>? = products?.map { product ->
            ApiDtoToDBEntity.map(product)
        }
        productEntities?.toTypedArray()
            ?.let { productDatabaseService.productDao().insertAllProducts(*it) }

    }

    /**
     *   Fetches from DB by default
     *   Clears the products from the DB if they are older than the given $cacheExpiryTime, returns fresh results from fresh API call
     *   It is possible to explicitly clear the cache and get fresh results from API by setting $forceInvalidateCatchAndReFetch
     */
    fun getAllProductsWithReFetchIfNeeded(
        forceInvalidateCatchAndReFetch: Boolean = false,
        cacheExpiryTimeInMills: Int? = null,
        searchKeyword: String? = null,
    ): List<ProductDO> {
        var resultProducts = getProductsFromDB(searchKeyword = searchKeyword)
        val isAnyOfTheCachedProductsExpired = isAnyOfTheCachedProductsExpired(
            resultProducts, cacheExpiryTimeInMills ?: AppConstants.CACHE_EXPIRY_TIME
        )

        if (isAnyOfTheCachedProductsExpired || resultProducts.isEmpty() || forceInvalidateCatchAndReFetch) {
            productDatabaseService.productDao().delete(*resultProducts.toTypedArray())
            getProductsFromApiAndCache()
            resultProducts = getProductsFromDB(searchKeyword = searchKeyword)
        }
        return resultProducts.map {
            DbEntityToDo.map(it)

        }
    }

    private fun isAnyOfTheCachedProductsExpired(
        products: List<ProductEntity>, cacheExpiryTimeInMills: Int = AppConstants.CACHE_EXPIRY_TIME
    ): Boolean {
        val isAnyOfTheCachedProductsExpired = products.any {
            (System.currentTimeMillis() - it.timestamp) > cacheExpiryTimeInMills
        }
        return isAnyOfTheCachedProductsExpired
    }

    private fun getProductsFromDB(searchKeyword: String? = null): List<ProductEntity> {

        val allProducts = if (searchKeyword.isNullOrEmpty()) {
            productDatabaseService.productDao().getAllProducts(ProductEntityColumnNames.NAME)

        } else {
            productDatabaseService.productDao()
                .searchAllProductsByName(searchKeyword, ProductEntityColumnNames.NAME)
        }

        return allProducts
    }

}