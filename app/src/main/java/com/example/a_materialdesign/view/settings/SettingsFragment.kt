package com.example.a_materialdesign.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_materialdesign.R
import com.example.a_materialdesign.databinding.SettingsFragmentBinding
import com.example.a_materialdesign.view.settings.recycler.RecyclerFragment

class SettingsFragment : Fragment() {

    private var _binding: SettingsFragmentBinding? = null
    private val binding: SettingsFragmentBinding
        get() {
            return _binding!!
        }

    private var controller: Controller? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        controller =
            if (context is Controller) context else throw IllegalStateException(
                "Activity IllegalStateException"
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyChipChoice()
        switchTheme()
        aboutAppButton()
        toRecyclerViewButton()
    }

    private fun toRecyclerViewButton() {
        binding.toRecyclerViewButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                .addToBackStack(null)
                .replace(R.id.container, RecyclerFragment()).commit()
        }
    }

    private fun aboutAppButton() {
        binding.aboutAppButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                .addToBackStack(null)
                .replace(R.id.container, AboutAppFragment()).commit()
        }
    }

    private fun switchTheme() {
        binding.grayThemeRadioButton.setOnClickListener {
            controller!!.setAppTheme(1)
        }

        binding.blueThemeRadioButton.setOnClickListener {
            controller!!.setAppTheme(2)
        }

        binding.purpleThemeRadioButton.setOnClickListener {
            controller!!.setAppTheme(3)
        }

        binding.orangeThemeRadioButton.setOnClickListener {
            controller!!.setAppTheme(4)
        }
    }

    private fun applyChipChoice() {
        binding.chipToday.setOnClickListener {
            controller!!.saveResult(0)
        }

        binding.chipYesterday.setOnClickListener {
            controller!!.saveResult(1)
        }

        binding.chipTwoDaysAgo.setOnClickListener {
            controller!!.saveResult(2)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SettingsFragment()
    }

    interface Controller {
        fun saveResult(result: Int)
        fun setAppTheme(theme: Int)
    }
}