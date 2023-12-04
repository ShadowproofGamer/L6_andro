package com.example.l5_andro

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ImagePagerAdapter(fa: Fragment): FragmentStateAdapter(fa) {
    private val _TAB_NUMBER = 4
    private var _position = 0

    override fun createFragment(position: Int): Fragment {
        this._position = position
        if (position == 0)
            return ImageFragment.newInstance(R.drawable.emotion_neutral)
        if (position == 1)
            return ImageFragment.newInstance(R.drawable.enemy_title)
        if (position == 2)
            return ImageFragment.newInstance(R.drawable.human_title)
        return ImageFragment.newInstance(R.drawable.npc_title)
    }

    override fun getItemCount(): Int {
        return _TAB_NUMBER
    }

    fun getCurrentItem(): Int {
        return this._position
    }
}