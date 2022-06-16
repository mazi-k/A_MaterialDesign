package com.example.a_materialdesign.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.a_materialdesign.databinding.SettingsFragmentBinding
import java.lang.IllegalStateException

class SettingsFragment: Fragment() {

    private var _binding: SettingsFragmentBinding ? = null
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

        binding.applyButton.setOnClickListener {
            var result = 2
            binding.chipGroup.setOnCheckedStateChangeListener { chip, isChecked ->
                when (chip.checkedChipId){
                    //TODO: how does it work??????
                }
            }
            controller!!.saveResult(result)
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
    }
}