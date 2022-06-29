package com.example.a_materialdesign.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_materialdesign.databinding.FragmentAboutAppBinding

class AboutAppFragment: Fragment() {
    private var _binding: FragmentAboutAppBinding? = null
    private val binding: FragmentAboutAppBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AboutAppFragment()
    }
}