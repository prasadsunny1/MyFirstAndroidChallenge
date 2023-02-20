package com.example.myfirstandroidchallenge

import android.content.Context
import com.example.myfirstandroidchallenge.data_sources.database.ProductDatabase
import com.example.myfirstandroidchallenge.data_sources.network.ProductAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Hilt module to provide dependencies
 * It can be use to inject dependencies to classes that are not part of our source code.
 * for eg. third party libraries, android framework classes and generated objects
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * Creates and injects a an instance of [ProductAPIService] to HILT
     */
    @Provides
    @Singleton
    fun provideRetrofitInstance() = ProductAPIService.create()

    /**
     * Creates and injects a an instance of [ProductDatabase] to HILT
     */
    @Provides
    @Singleton
    fun provideDatabaseServiceInstance(@ApplicationContext context: Context) =
        ProductDatabase.create(context)

    @Provides
    @Singleton
    fun provideCoRoutineScope(): CoroutineDispatcher = Dispatchers.IO

}