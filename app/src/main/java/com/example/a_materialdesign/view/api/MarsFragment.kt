package com.example.a_materialdesign.view.api

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.a_materialdesign.databinding.FragmentMarsBinding


class MarsFragment : Fragment() {

    private val duration = 1000L
    private var isAnimate = false

    private var _binding: FragmentMarsBinding? = null
    private val binding: FragmentMarsBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageAnimate()
    }

    private fun imageAnimate() {
        val view = binding.imageView
        view.setOnClickListener {
            val set = AnimatorSet()
            set.apply {
                playSequentially(
                    showAnimatorSet(view),
                    toNormalAnimatorSet(view)
                )
                addListener(getImageListener(view))
                start()
            }
        }
        view.animate().alphaBy(0f).alpha(1f).start()
    }

    private fun showAnimatorSet(view: View): AnimatorSet? {
        val set = AnimatorSet()
        set.setDuration(duration).playTogether(
            ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f),
            ObjectAnimator.ofFloat(view, View.SCALE_X, 0.2f, 1.4f),
            ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.2f, 1.4f)
        )
        return set
    }

    private fun toNormalAnimatorSet(view: View): AnimatorSet? {
        val set = AnimatorSet()
        set.setDuration(duration).playTogether(
            ObjectAnimator.ofFloat(view, View.SCALE_X, 1.4f, 1f),
            ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.4f, 1f)
        )
        return set
    }

    private fun getImageListener(view: ImageView): AnimatorListenerAdapter? {
        return object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                isAnimate = true
                view.apply {
                    visibility = View.VISIBLE
                    setLayerType(View.LAYER_TYPE_HARDWARE, null)
                }
            }
        }
    }
}