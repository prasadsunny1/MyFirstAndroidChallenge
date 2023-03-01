package com.example.myfirstandroidchallenge.data.databse.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfirstandroidchallenge.data.databse.configs.ProductDbConfig.DATABASE_NAME
import com.example.myfirstandroidchallenge.data.databse.dao.ProductDAO
import com.example.myfirstandroidchallenge.data.databse.entity.ProductEntity

/**
 * Room database for the product table
 */
@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDAO

    companion object Factory {

        /**
         * Create a room db instance of [ProductDatabase]
         */
        fun create(applicationContext: Context): ProductDatabase {
            return Room.databaseBuilder(
                applicationContext, ProductDatabase::class.java, DATABASE_NAME
            ).build()
        }
    }
}

