package com.example.myfirstandroidchallenge.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.myfirstandroidchallenge.view_model.ProductsViewModel
import com.example.myfirstandroidchallenge.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity() {
    private val productsViewModel: ProductsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigation()

        val editTextSearch = findViewById<TextInputEditText>(R.id.editTextSearch)
        editTextSearch.doAfterTextChanged { text ->
            // call search function on Product view model
            productsViewModel.searchProducts(text.toString())

        }
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        val bottomNavigationView: BottomNavigationView =
            findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

}