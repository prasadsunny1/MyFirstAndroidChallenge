package com.example.myfirstandroidchallenge.network

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfirstandroidchallenge.data.api.client.ProductHttpClient
import com.example.myfirstandroidchallenge.data.api.service.ProductAPIService
import com.example.myfirstandroidchallenge.data.databse.database.ProductDatabase
import org.junit.*
import org.junit.runner.*
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ProductAPIServiceTests {

    private lateinit var productAPIService: ProductAPIService

    @Before
    fun createRetrofitInstance() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val client = ProductHttpClient(context).buildHttpClient()
        productAPIService = ProductAPIService.create(client)
    }

    @After
    @Throws(IOException::class)
    fun cleanup() {
    }

    @Test
    fun test_get_products() {
        val response = productAPIService.getProducts().execute()
        Assert.assertTrue(response.isSuccessful)
    }
}