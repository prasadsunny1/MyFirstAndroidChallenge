package com.example.myfirstandroidchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstandroidchallenge.R
import com.example.myfirstandroidchallenge.view_model.ProductLoadStates
import com.example.myfirstandroidchallenge.view_model.ProductsViewModel
import com.example.myfirstandroidchallenge.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private val productsViewModel: ProductsViewModel by activityViewModels()
    private lateinit var binding: FragmentSecondBinding
    private lateinit var productListAdaptor: ProductListAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsViewModel.onViewCreated()

        val rvProducts: RecyclerView = binding.rvProductList
        productListAdaptor = ProductListAdaptor(activity, ProductListKind.Grid)

        rvProducts.layoutManager = GridLayoutManager(activity, 3)
        rvProducts.adapter = productListAdaptor

        binding.swipeRefreshLayout.setOnRefreshListener {
            productsViewModel.refreshProducts()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        // Observe loaded state changes and update the UI
        productsViewModel.productLoadStates.observe(viewLifecycleOwner) { productLoadState ->
            updateUI(productLoadState)
        }
    }

    private fun updateUI(value: ProductLoadStates?) {
        when (value) {
            ProductLoadStates.Loading -> {
                // Show loading indicator
                binding.circularLoaderView.visibility = View.VISIBLE
                binding.tvDataEmptyView.visibility = View.GONE
                binding.rvProductList.visibility = View.GONE
            }
            is ProductLoadStates.Loaded -> {
                // Hide loading indicator
                // Update UI
                binding.circularLoaderView.visibility = View.GONE
                binding.tvDataEmptyView.visibility = View.GONE
                binding.rvProductList.visibility = View.VISIBLE
                productListAdaptor.setProductList(value.products)

            }
            is ProductLoadStates.EmptyOrError -> {
                // Hide loading indicator
                // Show error message
                binding.circularLoaderView.visibility = View.GONE
                binding.tvDataEmptyView.visibility = View.VISIBLE
                binding.rvProductList.visibility = View.GONE
                binding.tvDataEmptyView.text = value.message
            }
            else -> {
                // Hide loading indicator
                // Show error message
                binding.circularLoaderView.visibility = View.GONE
                binding.tvDataEmptyView.visibility = View.VISIBLE
                binding.rvProductList.visibility = View.GONE
                binding.tvDataEmptyView.text = getString(R.string.product_load_failed_message)

            }
        }
    }
}