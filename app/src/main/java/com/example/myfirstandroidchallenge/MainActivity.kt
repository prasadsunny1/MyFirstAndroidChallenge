package com.example.myfirstandroidchallenge

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstandroidchallenge.models.Product
import com.example.myfirstandroidchallenge.views.ProductListAdaptor
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView: BottomNavigationView =
            findViewById<View>(R.id.bottomNavigationView) as BottomNavigationView
        setupWithNavController(bottomNavigationView, navController)
    }


}