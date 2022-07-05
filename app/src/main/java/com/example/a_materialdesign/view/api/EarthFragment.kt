package com.example.a_materialdesign.view.api

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_materialdesign.databinding.FragmentEarthBinding

class EarthFragment : Fragment() {

    private val duration = 2000L
    private var isAnimate = false

    private var _binding: FragmentEarthBinding? = null
    private val binding: FragmentEarthBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageAnimate()
    }

    private fun imageAnimate() {
        binding.imageView.setOnClickListener {
            isAnimate = !isAnimate
            if (isAnimate){
                ObjectAnimator.ofFloat(binding.imageView, View.ROTATION, 360f).setDuration(duration)
                    .start()
            } else {
                ObjectAnimator.ofFloat(binding.imageView, View.ROTATION, -360f).setDuration(duration)
                    .start()
            }

        }

    }
}