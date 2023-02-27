package com.example.myfirstandroidchallenge.data.api.client

import retrofit2.Retrofit

interface IProductHttpClient {

    fun buildHttpClient(): Retrofit
}