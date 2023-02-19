package com.example.myfirstandroidchallenge.data_sources.database

import androidx.room.*

@Dao
interface ProductDAO {

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM product_table ORDER BY :sortedBy")
    fun getAllProducts(sortedBy: ProductEntityColumnNames): List<ProductEntity>

    @Query("SELECT * FROM product_table WHERE id IN (:ids)")
    fun getAllProductsByIds(ids: IntArray): List<ProductEntity>

    @Query("SELECT * FROM product_table WHERE name LIKE :name")
    fun searchAllProductsByName(name: String): ProductEntity

    @Query("SELECT * FROM product_table WHERE name LIKE :name ORDER BY :sortedBy")
    fun searchAllProductsByName(name: String, sortedBy: ProductEntityColumnNames): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(vararg products: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProducts(vararg products: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)

    // Delete all given products
    @Delete
    fun delete(vararg products: ProductEntity)

}