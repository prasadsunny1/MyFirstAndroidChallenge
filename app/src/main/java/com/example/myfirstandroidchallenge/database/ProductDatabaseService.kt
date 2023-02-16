package com.example.myfirstandroidchallenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1)
abstract class ProductDatabaseService : RoomDatabase() {
    abstract fun productDao(): ProductDAO

    companion object Factory {
        fun create(applicationContext: Context): ProductDatabaseService {
            return Room.databaseBuilder(
                applicationContext,
                ProductDatabaseService::class.java, "database-name"
            ).build()
        }
    }

}

