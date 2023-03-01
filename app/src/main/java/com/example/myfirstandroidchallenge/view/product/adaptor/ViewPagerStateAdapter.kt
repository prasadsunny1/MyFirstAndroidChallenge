package com.example.myfirstandroidchallenge.view.product.adaptor

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myfirstandroidchallenge.view.product.fragment.BlankFragment
import com.example.myfirstandroidchallenge.view.product.fragment.ProductGridFragment
import com.example.myfirstandroidchallenge.view.product.fragment.ProductListFragment

/**
 * This class is used to define the view pager adapter for the view pager
 * @param activity: The activity which is using the view pager
 */
class ViewPagerStateAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProductListFragment()
            1 -> ProductGridFragment()
            else -> BlankFragment()
        }
    }
}