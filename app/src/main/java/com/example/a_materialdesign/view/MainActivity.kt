package com.example.a_materialdesign.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a_materialdesign.R
import com.example.a_materialdesign.utils.Parameters

class MainActivity : AppCompatActivity(), SettingsFragment.Controller {

    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_THEME = "theme"
    lateinit var mSettings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        if (mSettings.contains(APP_PREFERENCES_THEME)) {
            setAppTheme(mSettings.getInt(APP_PREFERENCES_THEME, 1))
        } else {
            setTheme(Parameters.getInstance().grayTheme)
        }
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance(0)).commit()
        }
    }

    override fun saveResult(result: Int) {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStack()
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

        val editor: SharedPreferences.Editor = mSettings.edit()
        editor.putInt(APP_PREFERENCES_THEME, theme)
        editor.apply()
    }
}