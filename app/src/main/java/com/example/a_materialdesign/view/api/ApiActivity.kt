package com.example.a_materialdesign.view.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a_materialdesign.databinding.ActivityApiBinding
import com.example.a_materialdesign.utils.Parameters

class ApiActivity : AppCompatActivity() {

    lateinit var binding: ActivityApiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(Parameters.getInstance().grayTheme)
        super.onCreate(savedInstanceState)

        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}