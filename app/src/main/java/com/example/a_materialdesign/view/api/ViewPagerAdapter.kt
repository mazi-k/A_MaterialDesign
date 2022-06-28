package com.example.a_materialdesign.view.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val ADAPTER_SIZE = 3
private const val EARTH_FRAGMENT = 0
private const val MARS_FRAGMENT = 1
private const val SYSTEM_FRAGMENT = 2

class ViewPagerAdapter(fragmentManager: FragmentActivity) :
    FragmentStateAdapter(fragmentManager) {

    override fun getItemCount(): Int {
        return ADAPTER_SIZE
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            EARTH_FRAGMENT -> EarthFragment()
            MARS_FRAGMENT -> MarsFragment()
            SYSTEM_FRAGMENT -> SystemFragment()
            else -> EarthFragment()
        }
    }
}