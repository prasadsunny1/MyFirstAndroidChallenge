package com.example.myfirstandroidchallenge.data_sources.database

/**
 * This class is used to define the column names of the [ProductEntity] table.
 */
enum class ProductEntityColumnNames {
    ID, NAME, PRICE, EXTRA, IMAGE, TIMESTAMP;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}