package com.example.myfirstandroidchallenge.data_sources.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfirstandroidchallenge.AppConstants

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDAO

    companion object Factory {
        /**
         * Create a room db instance of [ProductDatabase]
         */
        fun create(applicationContext: Context): ProductDatabase {
            return Room.databaseBuilder(
                applicationContext, ProductDatabase::class.java, AppConstants.DATABASE_NAME
            ).build()
        }
    }
}

