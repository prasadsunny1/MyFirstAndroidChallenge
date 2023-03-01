package com.example.myfirstandroidchallenge.data.databse.dao

import androidx.room.*
import com.example.myfirstandroidchallenge.data.databse.entity.ProductEntity
import com.example.myfirstandroidchallenge.data.databse.helpers.ProductEntityColumnNames

/**
 * DAO for the product table
 */
@Dao
interface ProductDAO {

    /**
     * Get all products
     */
    @Query("SELECT * FROM product_table")
    fun getAllProducts(): List<ProductEntity>

    /**
     * Get all products sorted by the given columnName
     */
    @Query("SELECT * FROM product_table ORDER BY :sortedBy")
    fun getAllProducts(sortedBy: ProductEntityColumnNames): List<ProductEntity>

    /**
     * Get a product by id
     */
    @Query("SELECT * FROM product_table WHERE id IN (:ids)")
    fun getAllProductsByIds(ids: IntArray): List<ProductEntity>

    /**
     * Search for a product by name
     */
    @Query("SELECT * FROM product_table WHERE name LIKE '%' || :name || '%'")
    fun searchAllProductsByName(name: String): List<ProductEntity>

    /**
     * Search for a product by name and sort by the given column
     */
    @Query("SELECT * FROM product_table WHERE name LIKE '%' || :name || '%' ORDER BY :sortedBy")
    fun searchAllProductsByName(
        name: String,
        sortedBy: ProductEntityColumnNames
    ): List<ProductEntity>

    /**
     * Insert a product
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(vararg products: ProductEntity)

    /**
     * Insert all given products
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProducts(vararg products: ProductEntity)

    /**
     * Delete a product
     */
    @Delete
    fun delete(product: ProductEntity)

    /**
     * Delete all given products
     */
    @Delete
    fun delete(vararg products: ProductEntity)
}