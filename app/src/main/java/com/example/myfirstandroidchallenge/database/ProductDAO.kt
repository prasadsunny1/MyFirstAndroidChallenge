package com.example.myfirstandroidchallenge.database

import androidx.room.*

@Dao
interface ProductDAO {

    @Query("SELECT * FROM product_table")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM product_table WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<ProductEntity>

    @Query("SELECT * FROM product_table WHERE name LIKE :name")
    fun findByName(name: String): ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg products: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)

    // Delete all given products
    @Delete
    fun delete(vararg products: ProductEntity)

}