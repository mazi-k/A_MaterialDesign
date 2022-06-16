package com.example.a_materialdesign.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a_materialdesign.R
import com.example.a_materialdesign.utils.Parameters

class MainActivity : AppCompatActivity(), SettingsFragment.Controller {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Parameters.getInstance().grayTheme)
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

    override fun setAppTheme(theme: Int) {
        when (theme) {
            1 -> setTheme(Parameters.getInstance().grayTheme)
            2 -> setTheme(Parameters.getInstance().blueTheme)
            3 -> setTheme(Parameters.getInstance().purpleTheme)
            4 -> setTheme(Parameters.getInstance().orangeTheme)
        }
    }
}