package com.example.a_materialdesign.utils

import com.example.a_materialdesign.R

class Parameters {
    var resetFragment: Boolean = false
    var grayTheme: Int = R.style.MyGrayTheme
    var blueTheme: Int = R.style.MyBlueTheme
    var purpleTheme: Int = R.style.MyPurpleTheme
    var orangeTheme: Int = R.style.MyOrangeTheme

    companion object {
        @Volatile
        private var INSTANCE: Parameters? = null
        fun getInstance(): Parameters {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Parameters()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}