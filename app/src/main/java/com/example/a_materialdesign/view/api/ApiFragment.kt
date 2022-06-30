package com.example.a_materialdesign.view.api

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_materialdesign.R
import com.example.a_materialdesign.databinding.FragmentApiBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ApiFragment : Fragment() {
    private var _binding: FragmentApiBinding? = null
    private val binding: FragmentApiBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApiBinding.inflate(inflater, container, false)
        binding.viewPager.adapter = ViewPagerAdapter(this.requireActivity())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayoutInit()
        switch()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun tabLayoutInit() {
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager,
            object : TabLayoutMediator.TabConfigurationStrategy {
                @SuppressLint("UseCompatLoadingForDrawables")
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = when (position) {
                        EARTH_FRAGMENT -> "Earth"
                        MARS_FRAGMENT -> "Mars"
                        SYSTEM_FRAGMENT -> "System"
                        else -> "Earth"
                    }

                    tab.icon = when (position) {
                        EARTH_FRAGMENT -> resources.getDrawable(R.drawable.ic_earth)
                        MARS_FRAGMENT -> resources.getDrawable(R.drawable.ic_mars)
                        SYSTEM_FRAGMENT -> resources.getDrawable(R.drawable.ic_system)
                        else -> resources.getDrawable(R.drawable.ic_earth)
                    }
                }
            }).attach()
    }

    private fun switch(){
        //todo: найти, где взять position
        when(position) {
            EARTH_FRAGMENT -> {
                binding.indicator1.imageAlpha = 100
                binding.indicator2.imageAlpha = 60
                binding.indicator3.imageAlpha = 60
            }
            MARS_FRAGMENT -> {
                binding.indicator1.imageAlpha = 60
                binding.indicator2.imageAlpha = 100
                binding.indicator3.imageAlpha = 60
            }
            SYSTEM_FRAGMENT -> {
                binding.indicator1.imageAlpha = 60
                binding.indicator2.imageAlpha = 60
                binding.indicator3.imageAlpha = 100
            }
            else -> {
                binding.indicator1.imageAlpha = 100
                binding.indicator2.imageAlpha = 60
                binding.indicator3.imageAlpha = 60
            }
        }

    }

    companion object {
        const val EARTH_FRAGMENT = 0
        const val MARS_FRAGMENT = 1
        const val SYSTEM_FRAGMENT = 2
        var position = 0

        @JvmStatic
        fun newInstance() =
            ApiFragment()
    }
}