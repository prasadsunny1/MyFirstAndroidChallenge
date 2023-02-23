package com.example.myfirstandroidchallenge.data_sources.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfirstandroidchallenge.AppConstants

/**
 * This class is used to define the table structure of the database
 * @param id: The id of the product
 * @param name: The name of the product
 * @param price: The price of the product
 * @param extra: The extra information of the product
 * @param image: The image of the product
 * @param timestamp: The timestamp of the product
 */
@Entity(tableName = AppConstants.TABLE_NAME)
data class ProductEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val price: String,
    @ColumnInfo val extra: String,
    @ColumnInfo val image: String,
    @ColumnInfo val timestamp: Long = System.currentTimeMillis()
)

