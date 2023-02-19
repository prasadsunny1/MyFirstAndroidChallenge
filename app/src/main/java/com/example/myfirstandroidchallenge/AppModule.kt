package com.example.myfirstandroidchallenge

import android.content.Context
import com.example.myfirstandroidchallenge.data_sources.database.ProductDatabase
import com.example.myfirstandroidchallenge.data_sources.network.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance() = ProductService.create()

    @Provides
    @Singleton
    fun provideDatabaseServiceInstance(@ApplicationContext context: Context) =
        ProductDatabase.create(context);

}