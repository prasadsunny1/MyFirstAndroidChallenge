package com.example.myfirstandroidchallenge.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstandroidchallenge.R
import com.example.myfirstandroidchallenge.models.ProductItem

 class ProductListAdaptor(private val context: Context? = null) : RecyclerView.Adapter<ProductViewHolder>() {
    private var productList:List<ProductItem> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(newList: List<ProductItem>) {
        productList = newList
        // Notify the adapter that the data has changed
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.product_list_item , parent, false)
        return ProductViewHolder(view)    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.productTitle.text = productList[position].name ?: "Title $position"
        holder.productSubtitle.text = productList[position].price ?: "Subtitle $position"
    }
    override fun getItemCount(): Int {
        return productList.size
    }
}