package com.example.a_materialdesign.view.api

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a_materialdesign.R
import com.example.a_materialdesign.databinding.ActivityApiBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ApiActivity : AppCompatActivity() {

    lateinit var binding: ActivityApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)

        tabLayoutInit()
        switch()
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
    }
}