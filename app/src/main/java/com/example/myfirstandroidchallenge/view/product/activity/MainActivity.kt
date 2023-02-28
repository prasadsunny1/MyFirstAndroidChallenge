package com.example.myfirstandroidchallenge.view.product.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.myfirstandroidchallenge.view_model.ProductsViewModel
import com.example.myfirstandroidchallenge.R
import com.example.myfirstandroidchallenge.databinding.ActivityMainBinding
import com.example.myfirstandroidchallenge.view.product.adaptor.ViewPagerStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val productsViewModel: ProductsViewModel by viewModels()
    private lateinit var viewPagerAdapter: ViewPagerStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setUpPagerNavigation()
        setupStatusBarColor()
        productsViewModel.onViewCreated()
        mBinding.headerSectionLayout.editTextSearch.doAfterTextChanged { text ->
            productsViewModel.searchProducts(text.toString())
        }
    }

    private fun setupStatusBarColor() {
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar_color)
    }

    private fun setUpPagerNavigation() {
        viewPagerAdapter = ViewPagerStateAdapter(this@MainActivity)
        mBinding.viewPager.adapter = viewPagerAdapter

        mBinding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.bottomNavigationView.selectedItemId =
                    mBinding.bottomNavigationView.menu.getItem(position).itemId
            }
        })
        mBinding.bottomNavigationView.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.productListFragment -> {
                    mBinding.viewPager.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.productGridFragment -> {
                    mBinding.viewPager.currentItem = 1
                    return@setOnItemSelectedListener true
                }
            }
            return@setOnItemSelectedListener false
        }
    }
}