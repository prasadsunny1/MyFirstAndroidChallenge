package com.example.myfirstandroidchallenge.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirstandroidchallenge.R
import com.example.myfirstandroidchallenge.data_sources.network.ProductItem
import com.example.myfirstandroidchallenge.databinding.ProductGridItemBinding
import com.example.myfirstandroidchallenge.databinding.ProductListItemBinding

class ProductListAdaptor(
    private val context: Context? = null,
    private val productListKind: ProductListKind = ProductListKind.Linear
) : RecyclerView.Adapter<ProductViewHolder>() {
    private var productList: List<ProductItem> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(newList: List<ProductItem>) {
        productList = newList
        // Notify the adapter that the data has changed
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View =
            if (productListKind == ProductListKind.Grid) ProductGridItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ).root
            else ProductListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ).root
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.tvProductName.text = productList[position].name
        holder.tvProductPrice.text = productList[position].price
        holder.tvProductExtraInfo?.text = productList[position].extra
        if (context != null) {
            Glide.with(context).load(productList[position].image).into(holder.tvProductImage)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}