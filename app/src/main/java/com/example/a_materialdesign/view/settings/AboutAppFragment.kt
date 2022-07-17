package com.example.a_materialdesign.view.settings

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

    lateinit var spannableRainbow: SpannableString//объеденяет все

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutAppBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scrollFade()
        textBullet()
        titleRainbow()
    }

    private fun titleRainbow() {
        binding.title.typeface =
            Typeface.createFromAsset(requireActivity().assets, "new_font.otf")
        spannableRainbow = SpannableString(getString(R.string.about_app))
        rainbow(1)
    }

    fun rainbow(i:Int=1) {
        var currentCount = i
        val x = object : CountDownTimer(20000, 200) {
            override fun onTick(millisUntilFinished: Long) {
                colorText(currentCount)
                currentCount = if (++currentCount>5) 1 else currentCount
            }
            override fun onFinish() {
                rainbow(currentCount)
            }
        }
        x.start()
    }


    private fun colorText(colorFirstNumber:Int){
        binding.title.setText(spannableRainbow, TextView.BufferType.SPANNABLE)
        spannableRainbow = binding.title.text as SpannableString
        val map = mapOf(
            0 to ContextCompat.getColor(requireContext(), R.color.blue_200),
            1 to ContextCompat.getColor(requireContext(), R.color.blue_400),
            2 to ContextCompat.getColor(requireContext(), R.color.blue_800),
            3 to ContextCompat.getColor(requireContext(), R.color.blue_gray_800),
            4 to ContextCompat.getColor(requireContext(), R.color.blue_gray_600),
            5 to ContextCompat.getColor(requireContext(), R.color.blue_400),
            6 to ContextCompat.getColor(requireContext(),R.color.blue_gray_200)
        )
        val spans = spannableRainbow.getSpans(
            0, spannableRainbow.length,
            ForegroundColorSpan::class.java
        )
        for (span in spans) {
            spannableRainbow.removeSpan(span)
        }

        var colorNumber = colorFirstNumber
        for (i in 0 until binding.title.text.length) {
            if (colorNumber == 5) colorNumber = 0 else colorNumber += 1
            spannableRainbow.setSpan(
                ForegroundColorSpan(map.getValue(colorNumber)),
                i, i + 1,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
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