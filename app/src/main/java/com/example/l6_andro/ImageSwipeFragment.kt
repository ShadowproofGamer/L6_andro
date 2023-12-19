package com.example.l6_andro

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.l6_andro.databinding.FragmentImageSwipeBinding
import com.example.l6_andro.lab6.ImageItem
import com.example.l6_andro.lab6.ImageRepo

class ImageSwipeFragment : Fragment() {
    private lateinit var _binding: FragmentImageSwipeBinding
    private lateinit var _adapter: ImagePagerAdapter
    private lateinit var imageUrls: MutableList<ImageItem>
    private lateinit var imageRepo: ImageRepo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageRepo = ImageRepo.getInstance(requireContext())
        imageUrls = imageRepo.getSharedList()!!
        _adapter = ImagePagerAdapter(this, imageUrls)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentImageSwipeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = _binding.viewPager2

        viewPager.adapter = _adapter

        // Retrieve the photo path from the arguments
        val photoPath = arguments?.getString("path")
        // Find the position of the photo in the list
        val position = imageUrls.indexOfFirst { it.uripath == photoPath }
        // Set the current item of the ViewPager to this position
        if (position != -1) {
            viewPager.setCurrentItem(position, false)
        }



        _binding.imageSaveButton.setOnClickListener { _ ->
            val item = viewPager.currentItem
            val image = _adapter.getImage(item)

            val data: SharedPreferences = requireActivity().getSharedPreferences("L5_preferences", Context.MODE_PRIVATE)
            val editor = data.edit()
            editor.putString("imageUri", image.uripath)
            editor.apply()
            findNavController().navigate(R.id.action_global_mainFragment)
        }
    }

}