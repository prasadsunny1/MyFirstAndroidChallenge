package com.example.myfirstandroidchallenge.view.product.viewholder

import android.content.Context

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfirstandroidchallenge.databinding.ProductGridItemBinding
import com.example.myfirstandroidchallenge.model.ProductDO

class ProductGridViewHolder(private var binding: ProductGridItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(p: ProductDO, ctx: Context?) {
        binding.tvProductName.text = p.name
        binding.tvProductPrice.text = p.price
        ctx?.let { Glide.with(it).load(p.image).into(binding.imgViewProductImage) }
    }
}