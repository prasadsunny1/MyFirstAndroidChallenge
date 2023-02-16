package com.example.myfirstandroidchallenge

import com.example.myfirstandroidchallenge.database.ProductDatabaseService
import com.example.myfirstandroidchallenge.database.ProductEntity
import com.example.myfirstandroidchallenge.models.ProductItem
import javax.inject.Inject

class ProductRepository
@Inject constructor(
    private val productService: ProductService,
    private val productDatabaseService: ProductDatabaseService,
) {

    // Get products from service and return product or throw error
    private fun getProductsFromApiAndCache(): List<ProductItem>? {
        val response = productService.getProducts().execute()
        if (response.errorBody() != null) {
            return null
        } else {
            saveProductsToDB(response.body()?.data?.productItems)
            return response.body()?.data?.productItems
        }
    }

    private fun saveProductsToDB(products: List<ProductItem>?) {
        val productEntities: List<ProductEntity>? = products?.map { product ->
            ProductEntity(
                id = product.hashCode(),
                name = product.name ?: "",
                price = product.price ?: "",
                extra = product.extra ?: "",
                image = product.image ?: "",
            )
        }
        productEntities?.toTypedArray()?.let { productDatabaseService.productDao().insertAll(*it) }

    }

    /**
     *   Fetches from DB by default
     *   Clears the products from the DB if they are older than the given $cacheExpiryTime, returns fresh results from fresh API call
     *   It is possible to explicitly clear the cache and get fresh results from API by setting $forceInvalidateCatchAndReFetch
     */
    fun getAllProductsWithReFetchIfNeeded(
        forceInvalidateCatchAndReFetch: Boolean = false,
        cacheExpiryTimeInMills: Int = 1000 * 24 * 3600
    ): List<ProductItem>? {
        val allProducts = productDatabaseService.productDao().getAll()
        // This is cache expiry time
        val isAnyOfTheCachedProductsExpired = allProducts.any {
            (System.currentTimeMillis() - it.timestamp) > cacheExpiryTimeInMills
        }
        return if (isAnyOfTheCachedProductsExpired || allProducts.isEmpty() || forceInvalidateCatchAndReFetch) {
            productDatabaseService.productDao().delete(*allProducts.toTypedArray())
            getProductsFromApiAndCache()
        } else {
            allProducts.map {
                ProductItem(
                    name = it.name,
                    price = it.price,
                    extra = it.extra,
                    image = it.image,
                )
            }
        }
    }
}