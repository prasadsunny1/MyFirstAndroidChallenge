package com.example.myfirstandroidchallenge.test_data

import com.example.myfirstandroidchallenge.data.databse.entity.ProductEntity
import com.example.myfirstandroidchallenge.model.ProductDO

object TestData {

    private val productDO = ProductDO(
        "2 rs",
        "Item 1",
        "Same day shipping",

        "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
    )
    val ProductDOs = listOf(productDO);
}
