package com.example.myfirstandroidchallenge.views

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstandroidchallenge.R

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var productTitle: TextView = itemView.findViewById(R.id.tvTitle)
    var productSubtitle: TextView = itemView.findViewById(R.id.tvSubtitle)

    init {
        productTitle = itemView.findViewById(R.id.tvTitle)
        productTitle = itemView.findViewById(R.id.tvTitle)
        productSubtitle = itemView.findViewById(R.id.tvSubtitle)
    }
}