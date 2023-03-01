package com.example.myfirstandroidchallenge.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myfirstandroidchallenge.data.databse.dao.ProductDAO
import com.example.myfirstandroidchallenge.data.databse.database.ProductDatabase
import org.junit.*
import org.junit.runner.*
import java.io.IOException

@RunWith(AndroidJUnit4::class)
internal class ProductDatabaseTests {

    private lateinit var db: ProductDatabase
    private lateinit var productDAO: ProductDAO

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ProductDatabase::class.java).build()
        productDAO = db.productDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun test_save_1_product_item() {
        DbTestingHelper.addOneProductsToDb(productDAO)
        Assert.assertEquals(1, productDAO.getAllProducts().size)
    }

    @Test
    fun test_search_by_name_positive() {
        DbTestingHelper.add10ProductsToDb(productDAO)
        val searchResult = productDAO.searchAllProductsByName("Product name 7")
        searchResult.onEach {

            assert(it.name.contains("Product name 7"))
        }
    }

    @Test
    fun test_search_by_name_negative() {
        DbTestingHelper.add10ProductsToDb(productDAO)
        val searchResult = productDAO.searchAllProductsByName("sunny")
        assert(searchResult.isEmpty())
    }
}

// Repository tests should include
// - Test that the repository returns the correct data from the API when calling the method
// - Test that the repository returns the correct data from the database when calling the method
// Make sure correct object is returned when searching for a product by name
// Make sure correct error is thrown when API/db fails, does not have results
