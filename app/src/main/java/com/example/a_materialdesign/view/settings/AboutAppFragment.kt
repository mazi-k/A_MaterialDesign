package com.example.a_materialdesign.view.settings

import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.a_materialdesign.R
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

        scrollFade()
        textBullet()
    }

    private fun textBullet() {
        val text = requireActivity().resources.getString(R.string.large_text)
        val spannableString = SpannableString(text)
        val list = text.indexesOf("\n")
        var current = 0
        list?.forEach {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                spannableString.setSpan(
                    BulletSpan(
                        20,
                        ContextCompat.getColor(requireContext(), R.color.colorAccent),
                        5
                    ), current, it, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                binding.largeText.text = spannableString
            }
            current = it + 1
        }

    }

    private fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int>? =
        this?.let {
            (if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr))
                .findAll(it).map { it.range.first }.toList()
        }

    private fun scrollFade() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
                binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
            }
        }
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