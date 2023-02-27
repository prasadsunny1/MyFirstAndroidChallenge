package com.example.myfirstandroidchallenge.view.product.adaptor

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirstandroidchallenge.databinding.ProductGridItemBinding
import com.example.myfirstandroidchallenge.databinding.ProductListItemBinding
import com.example.myfirstandroidchallenge.model.ProductDO
import com.example.myfirstandroidchallenge.view.product.enums.ProductListKind
import com.example.myfirstandroidchallenge.view.product.enums.ProductListKind.Grid
import com.example.myfirstandroidchallenge.view.product.enums.ProductListKind.Linear
import com.example.myfirstandroidchallenge.view.product.viewholder.ProductViewHolder

class ProductListAdaptor(
    private val context: Context? = null,
    private val productListKind: ProductListKind = Linear
) : RecyclerView.Adapter<ProductViewHolder>() {

    private var productList: List<ProductDO> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(newList: List<ProductDO>) {
        productList = newList
        // Notify the adapter that the data has changed
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View =
            if (productListKind == Grid) ProductGridItemBinding.inflate(
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