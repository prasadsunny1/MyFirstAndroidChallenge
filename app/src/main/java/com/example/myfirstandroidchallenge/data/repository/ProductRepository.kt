package com.example.myfirstandroidchallenge.data.repository

import com.example.myfirstandroidchallenge.data.api.dto.ProductItemDTO
import com.example.myfirstandroidchallenge.data.api.exception.NetworkException
import com.example.myfirstandroidchallenge.data.api.service.ProductAPIService
import com.example.myfirstandroidchallenge.data.databse.configs.ProductDbConfig.CACHE_EXPIRY_TIME
import com.example.myfirstandroidchallenge.data.databse.database.ProductDatabase
import com.example.myfirstandroidchallenge.data.databse.entity.ProductEntity
import com.example.myfirstandroidchallenge.data.databse.helpers.ProductEntityColumnNames
import com.example.myfirstandroidchallenge.model.ProductDO
import com.example.myfirstandroidchallenge.model.mapper.ApiDtoToDBEntity
import com.example.myfirstandroidchallenge.model.mapper.DbEntityToDo
import javax.inject.Inject

/**
 * This class is used to define the repository methods for the product table
 */
class ProductRepository
@Inject constructor(
    private val productAPIService: ProductAPIService,
    private val productDatabaseService: ProductDatabase,
) : IProductRepository {

    // Get products from service and return product or throw error
    private fun getProductsFromApiAndCache(): List<ProductItemDTO>? {
        try {
            val response = productAPIService.getProducts().execute()
            if (response.errorBody() != null) {
                return null
            } else {
                saveProductsToDB(response.body()?.data?.productItems)
                return response.body()?.data?.productItems
            }
        } catch (e: NetworkException) {
            return null
            // Do something with the exception
        } catch (e: Exception) {
            return null
            // Do something with the exception
        }
    }

    private fun saveProductsToDB(products: List<ProductItemDTO>?) {
        val productEntities: List<ProductEntity>? = products?.map { product ->
            ApiDtoToDBEntity.map(product)
        }
        productEntities?.toTypedArray()?.let { productDatabaseService.productDao().insertAllProducts(*it) }
    }

    /**
     *   Fetches from DB by default
     *   Clears the products from the DB if they are older than the given $cacheExpiryTime, returns fresh results from fresh API call
     *   It is possible to explicitly clear the cache and get fresh results from API by setting $forceInvalidateCatchAndReFetch
     */
    override suspend fun getAllProductsWithReFetchIfNeeded(
        forceInvalidateCatchAndReFetch: Boolean,
        cacheExpiryTimeInMills: Int?,
        searchKeyword: String?,
    ): List<ProductDO> {
        var resultProducts = getProductsFromDB(searchKeyword = searchKeyword)
        val isAnyOfTheCachedProductsExpired = isAnyOfTheCachedProductsExpired(
            resultProducts, cacheExpiryTimeInMills ?: CACHE_EXPIRY_TIME
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
        products: List<ProductEntity>, cacheExpiryTimeInMills: Int = CACHE_EXPIRY_TIME
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
            productDatabaseService.productDao().searchAllProductsByName(searchKeyword, ProductEntityColumnNames.NAME)
        }

        return allProducts
    }
}