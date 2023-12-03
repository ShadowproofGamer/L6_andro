package com.example.l5_andro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter2(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    companion object {
        const val PAGE_COUNT = 2 // Replace this with the actual number of tabs/pages
    }

    override fun getItemCount(): Int {
        return PAGE_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        // Return the proper fragment for each position value
        return when (position) {
            0 -> Fragment1.newInstance("f1","page 1")
            1 -> Fragment2.newInstance("f2","page 2")
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

}
