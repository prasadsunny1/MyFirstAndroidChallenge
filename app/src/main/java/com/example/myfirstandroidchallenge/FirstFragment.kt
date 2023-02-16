package com.example.myfirstandroidchallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstandroidchallenge.databinding.FragmentFirstBinding
import com.example.myfirstandroidchallenge.views.ProductListAdaptor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentFirstBinding
    private lateinit var productListAdaptor: ProductListAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        viewModel.getProducts()


        val rvProducts: RecyclerView = binding.rvProductList
        productListAdaptor = ProductListAdaptor(activity)
        rvProducts.layoutManager = LinearLayoutManager(activity)
        rvProducts.adapter = productListAdaptor


        // Observe loaded state changes and update the UI
        viewModel.productLoadStates.observe(viewLifecycleOwner) { abc ->
            updateUI(abc)
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
                binding.tvDataEmptyView.text = "Something went wrong while loading products"

            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) = FirstFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }
}