package com.example.myfirstandroidchallenge.data.api.client

import retrofit2.Retrofit

/**
 * Interface to build a Retrofit instance
 */
interface IProductHttpClient {

    fun buildHttpClient(): Retrofit
}