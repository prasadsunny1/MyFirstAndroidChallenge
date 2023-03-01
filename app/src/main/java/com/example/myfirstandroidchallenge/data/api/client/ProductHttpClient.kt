package com.example.myfirstandroidchallenge.data.api.client

import android.content.Context
import com.example.myfirstandroidchallenge.data.api.config.ProductApiConfig.BASE_URL
import com.example.myfirstandroidchallenge.data.api.interceptor.NetworkConnectionInterceptor
import com.example.myfirstandroidchallenge.data.api.interceptor.RetryInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Inject

/**
 * Class to build a Retrofit instance
 */
class ProductHttpClient @Inject constructor(@ApplicationContext private val mApplicationContext: Context) :
    IProductHttpClient {

    override fun buildHttpClient(): Retrofit {
        val oktHttpClient = Builder().addInterceptor(NetworkConnectionInterceptor(mApplicationContext))
            .addInterceptor(RetryInterceptor())

        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create())
            .client(oktHttpClient.build()).build()
    }
}