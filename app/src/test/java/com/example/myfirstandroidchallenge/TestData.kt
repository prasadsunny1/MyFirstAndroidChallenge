package com.example.myfirstandroidchallenge

import com.example.myfirstandroidchallenge.data_sources.database.ProductEntity

class TestData {

    companion object {
        val productEntity = ProductEntity(
            1,
            "pName",
            "pPrice",
            "Same day shipping",
            "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
        )
    }
}