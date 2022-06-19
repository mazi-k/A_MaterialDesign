package com.example.a_materialdesign.view.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_materialdesign.R
import com.example.a_materialdesign.databinding.FragmentBaseBinding

class BaseFragment : Fragment() {

    private var _binding: FragmentBaseBinding? = null
    private val binding: FragmentBaseBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBaseBinding.inflate(inflater, container, false)
        arguments?.let {
            setImage(it.getInt(BUNDLE_KEY))
        }
        return binding.root
    }

    private fun setImage(image: Int) {
        when (image) {
            EARTH_FRAGMENT -> {
                binding.image.setImageResource(R.drawable.image_bg_earth)
                binding.indicator1.imageAlpha = 100
                binding.indicator2.imageAlpha = 60
                binding.indicator3.imageAlpha = 60
            }
            MARS_FRAGMENT -> {
                binding.image.setImageResource(R.drawable.image_bg_mars)
                binding.indicator1.imageAlpha = 60
                binding.indicator2.imageAlpha = 100
                binding.indicator3.imageAlpha = 60
            }
            SYSTEM_FRAGMENT -> {
                binding.image.setImageResource(R.drawable.image_bg_system)
                binding.indicator1.imageAlpha = 60
                binding.indicator2.imageAlpha = 60
                binding.indicator3.imageAlpha = 100
            }
            else -> {
                binding.image.setImageResource(R.drawable.image_bg_earth)
                binding.indicator1.imageAlpha = 100
                binding.indicator2.imageAlpha = 60
                binding.indicator3.imageAlpha = 60
            }
        }

    }

    companion object {
        internal const val EARTH_FRAGMENT = 0
        internal const val MARS_FRAGMENT = 1
        internal const val SYSTEM_FRAGMENT = 2
        private const val BUNDLE_KEY = "key"

        @JvmStatic
        fun newInstance(type: Int): Fragment {
            return BaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(BUNDLE_KEY, type)
                }
            }
        }
    }
}