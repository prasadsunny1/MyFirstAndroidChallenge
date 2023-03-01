package com.example.myfirstandroidchallenge.database

import com.example.myfirstandroidchallenge.data.databse.dao.ProductDAO
import com.example.myfirstandroidchallenge.data.databse.entity.ProductEntity

object DbTestingHelper {

    // add 1 item to the database
    fun addOneProductsToDb(productDAO: ProductDAO) {
        val items: List<ProductEntity> = listOf(
            ProductEntity(
                extra = "Extra 1",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "1",
                name = "Product name 1"
            )
        )


        productDAO.insertAllProducts(*items.toTypedArray())
    }

    fun add10ProductsToDb(productDAO: ProductDAO) {
        val products: List<ProductEntity> = listOf(
            ProductEntity(
                extra = "Extra 1",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "1",
                name = "Product name 1"
            ),
            ProductEntity(
                extra = "Extra 2",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "2",
                name = "Product name 2"
            ),
            ProductEntity(
                extra = "Extra 3",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "3",
                name = "Product name 3"
            ),
            ProductEntity(
                extra = "Extra 4",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "4",
                name = "Product name 4"
            ),
            ProductEntity(
                extra = "Extra 5",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "5",
                name = "Product name 5"
            ),
            ProductEntity(
                extra = "Extra 6",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "6",
                name = "Product name 6"
            ),
            ProductEntity(
                extra = "Extra 7",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "7",
                name = "Product name 7"
            ),
            ProductEntity(
                extra = "Extra 8",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "8",
                name = "Product name 8"
            ),
            ProductEntity(
                extra = "Extra 9",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "9",
                name = "Product name 9"
            ),
            ProductEntity(
                extra = "Extra 10",
                image = "https://hamcrest.org/images/logo.jpg",
                price = "10",
                name = "Product name 10"
            )
        )
        productDAO.insertAllProducts(*products.toTypedArray())
    }
}