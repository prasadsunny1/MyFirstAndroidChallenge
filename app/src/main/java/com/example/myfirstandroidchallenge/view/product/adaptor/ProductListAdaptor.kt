package com.example.myfirstandroidchallenge.view.product.adaptor

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstandroidchallenge.databinding.ProductGridItemBinding
import com.example.myfirstandroidchallenge.databinding.ProductListItemBinding
import com.example.myfirstandroidchallenge.model.ProductDO
import com.example.myfirstandroidchallenge.view.product.enums.ProductListKind
import com.example.myfirstandroidchallenge.view.product.enums.ProductListKind.Grid
import com.example.myfirstandroidchallenge.view.product.enums.ProductListKind.Linear
import com.example.myfirstandroidchallenge.view.product.viewholder.ProductGridViewHolder
import com.example.myfirstandroidchallenge.view.product.viewholder.ProductListViewHolder

class ProductListAdaptor(
    private val context: Context? = null, private val productListKind: ProductListKind = Linear,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var productList: List<ProductDO> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(newList: List<ProductDO>) {
        productList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)

        return when (productListKind) {
            Linear -> {
                val binding = ProductListItemBinding.inflate(inflater, parent, false)
                ProductListViewHolder(binding)
            }
            Grid -> {
                val binding = ProductGridItemBinding.inflate(inflater, parent, false)
                ProductGridViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (productListKind) {
            Linear -> (holder as ProductListViewHolder).bind(productList[position], context)
            Grid -> (holder as ProductGridViewHolder).bind(productList[position], context)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}