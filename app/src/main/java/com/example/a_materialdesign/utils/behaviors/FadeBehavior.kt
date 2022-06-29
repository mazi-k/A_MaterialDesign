package com.example.a_materialdesign.utils.behaviors

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


class FadeBehavior(context: Context, attr: AttributeSet?=null):
    CoordinatorLayout.Behavior<View>(context,attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {

        if(dependency is AppBarLayout){
            child.alpha = 1-abs(dependency.y*2/dependency.height.toFloat())
        }


        return true
    }
}