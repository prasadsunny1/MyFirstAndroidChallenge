package com.example.myfirstandroidchallenge.view.product.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirstandroidchallenge.databinding.ProductListItemBinding
import com.example.myfirstandroidchallenge.model.ProductDO

/**
 * This class is used to define the view holder for the product list
 */
class ProductListViewHolder(private var binding: ProductListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(p: ProductDO, ctx: Context?) {
        binding.tvProductName.text = p.name
        binding.tvProductPrice.text = p.price
        binding.tvProductExtraInfo.text = p.extra
        ctx?.let { Glide.with(it).load(p.image).into(binding.imgViewProductImage) }
    }
}