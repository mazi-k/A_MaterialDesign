package com.example.a_materialdesign.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a_materialdesign.R

class MainActivity : AppCompatActivity(), SettingsFragment.Controller {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance(0)).commit()
        }
    }

    override fun saveResult(result: Int) {
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.container, PictureOfTheDayFragment.newInstance(result))
            .commit()
    }
}