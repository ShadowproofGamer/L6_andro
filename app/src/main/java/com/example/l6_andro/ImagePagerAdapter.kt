package com.example.l6_andro

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.l6_andro.lab6.ImageItem

class ImagePagerAdapter(fa: Fragment, images: MutableList<ImageItem>): FragmentStateAdapter(fa) {
    private var _position = 0
    private var images: MutableList<ImageItem> = images

    override fun createFragment(position: Int): Fragment {
        this._position = position
        Log.w("try image", position.toString())
        return ImageFragment.newInstance(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun getCurrentItem(): Int {
        return this._position
    }
    fun getImage(position: Int): ImageItem {
        return images[position]
    }
}




