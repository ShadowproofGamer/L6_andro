package com.example.l6_andro.lab6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.l6_andro.R

class ImageSliderFragment : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var imageUrls: MutableList<ImageItem>
    private lateinit var imageRepo: ImageRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageRepo = ImageRepo.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_slider, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = view.findViewById(R.id.viewPagerSl)
        imageUrls = imageRepo.getSharedList()!!

        val adapter = ImageSliderAdapter(imageUrls)
        viewPager.adapter = adapter

        // Retrieve the photo path from the arguments
        val photoPath = arguments?.getString("path")
        // Find the position of the photo in the list
        val position = imageUrls.indexOfFirst { it.uripath == photoPath }
        // Set the current item of the ViewPager to this position
        if (position != -1) {
            viewPager.setCurrentItem(position, false)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ImageSliderFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}