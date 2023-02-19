package com.example.myfirstandroidchallenge.data_sources.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class ProductEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val name: String,
    @ColumnInfo val price: String,
    @ColumnInfo val extra: String,
    @ColumnInfo val image: String,
    @ColumnInfo val timestamp: Long = System.currentTimeMillis()
)

fun add() {
}

