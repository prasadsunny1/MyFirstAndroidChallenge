package com.example.myfirstandroidchallenge.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstandroidchallenge.R

internal class ProductListAdaptor(private val context: Context? = null) : RecyclerView.Adapter<ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.product_list_item , parent, false)
        return ProductViewHolder(view)    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productTitle.text = "Title $position"
        holder.productSubtitle.text = "Subtitle $position"
    }
    override fun getItemCount(): Int {
        return 10
    }
}