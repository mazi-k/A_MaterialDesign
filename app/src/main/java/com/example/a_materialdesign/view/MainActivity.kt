package com.example.a_materialdesign.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.a_materialdesign.R
import com.example.a_materialdesign.databinding.ActivityMainBinding
import com.example.a_materialdesign.utils.Parameters
import com.example.a_materialdesign.view.api.ApiFragment
import com.example.a_materialdesign.view.settings.SettingsFragment

class MainActivity : AppCompatActivity(), SettingsFragment.Controller {

    val APP_PREFERENCES = "mysettings"
    val APP_PREFERENCES_THEME = "theme"
    lateinit var mSettings: SharedPreferences
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        if (mSettings.contains(APP_PREFERENCES_THEME)) {
            setAppTheme(mSettings.getInt(APP_PREFERENCES_THEME, 1))
        } else {
            setTheme(Parameters.getInstance().grayTheme)
        }

        setContentView(binding.root)
        init()
        setupNavigation()

        if (savedInstanceState == null) {
            navigationTo(PictureOfTheDayFragment.newInstance(0))
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

    private fun setupNavigation() {
        binding.bottomNavigationView.selectedItemId = R.id.action_bottom_view_home
    }

    private fun init() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_bottom_view_home -> {
                    navigationTo(PictureOfTheDayFragment.newInstance(0))
                    true
                }
                R.id.action_bottom_view_settings -> {
                    navigationTo(SettingsFragment.newInstance())
                    true
                }
                R.id.action_bottom_view_telescope -> {
                    navigationTo(ApiFragment.newInstance())
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    private fun navigationTo(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        fragmentManager.apply {
            beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }
}