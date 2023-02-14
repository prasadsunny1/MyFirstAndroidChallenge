package com.example.myfirstandroidchallenge

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstandroidchallenge.models.Product
import com.example.myfirstandroidchallenge.views.ProductListAdaptor


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
var products = listOf<Product>(
    Product("Title 1", "Subtitle 1"),
    Product("Title 2", "Subtitle 2"),
    Product("Title 3", "Subtitle 3"),
)


    }
    private fun init() {
        val rlProducts = findViewById<RecyclerView>(R.id.rvProductList);
        rlProducts.layoutManager = LinearLayoutManager(this)
        rlProducts.adapter = ProductListAdaptor(this)
    }


}