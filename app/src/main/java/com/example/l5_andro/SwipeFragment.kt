package com.example.l5_andro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SwipeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_swipe, container, false)

        val vpAdapter = MyPagerAdapter2(requireActivity())


        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = vpAdapter


        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)


        TabLayoutMediator(tabLayout, viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Tab 1"
                    //tab.setIcon(R.drawable.ic_launcher_foreground)
                }
                1 -> {
                    tab.text = "Tab 2"
                    //tab.setIcon(R.drawable.ic_launcher_foreground)
                }
            }
        }.attach()



        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SwipeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}