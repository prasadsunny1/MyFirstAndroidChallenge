package com.example.myfirstandroidchallenge.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstandroidchallenge.R

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvProductName: TextView = itemView.findViewById(R.id.tvProductName)
    var tvProductPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
    var tvProductExtraInfo: TextView = itemView.findViewById(R.id.tvProductExtraInfo)
    var tvProductImage: ImageView = itemView.findViewById(R.id.imgViewProductImage)

    init {
        tvProductName = itemView.findViewById(R.id.tvProductName)
        tvProductPrice = itemView.findViewById(R.id.tvProductPrice)
        tvProductExtraInfo = itemView.findViewById(R.id.tvProductExtraInfo)
        tvProductImage = itemView.findViewById(R.id.imgViewProductImage)
    }
}