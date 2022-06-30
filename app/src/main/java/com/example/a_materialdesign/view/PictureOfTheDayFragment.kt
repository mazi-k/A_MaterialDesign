package com.example.a_materialdesign.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.AnticipateOvershootInterpolator
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import coil.load
import com.example.a_materialdesign.R
import com.example.a_materialdesign.databinding.FragmentPictureOfTheDayBinding
import com.example.a_materialdesign.viewmodel.AppState
import com.example.a_materialdesign.viewmodel.PictureOfTheDayViewModel
import java.time.LocalDate

class PictureOfTheDayFragment : Fragment() {

    private var isMain = true
    private var result: Int = 0
    var flag = false

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding
        get() {
            return _binding!!
        }

    val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        result = requireArguments().getInt("dayResult")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        viewModel.getLiveDataForViewToObserve().observe(viewLifecycleOwner) {
            renderData(it)
        }

        val newDate = LocalDate.now().minusDays(result.toLong())

        when (result) {
            0 -> viewModel.sendServerRequest()
            1, 2 -> viewModel.sendServerRequestTemp(newDate.toString())
        }

        wikiSearch()
        imageViewAnimate()
    }

    private fun imageViewAnimate() {
        val constraintSetStart = ConstraintSet()
        constraintSetStart.clone(binding.root)

        binding.imageView.setOnClickListener {
            flag = !flag
            val changeBounds = ChangeBounds()
            changeBounds.duration = 1000L
            changeBounds.interpolator = AnticipateOvershootInterpolator(5.0f)
            TransitionManager.beginDelayedTransition(binding.root, changeBounds)
            if(flag){
                constraintSetStart.connect(R.id.title,
                    ConstraintSet.END,
                    R.id.main,
                    ConstraintSet.END)
                constraintSetStart.applyTo(binding.root)
            }else{
                constraintSetStart.connect(R.id.title,
                    ConstraintSet.END,
                    R.id.main,
                    ConstraintSet.START)
                constraintSetStart.applyTo(binding.root)
            }
        }
    }

    private fun wikiSearch() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.imageView.setImageResource(R.drawable.ic_load_error_vector)
            }
            is AppState.Loading -> {
                binding.imageView.setImageResource(R.drawable.ic_no_photo_vector)
            }
            is AppState.Success -> {
                binding.title.text = appState.serverResponseData.title
                binding.imageView.load(appState.serverResponseData.url) {
                    // TODO placehilde+error+transform
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(dayResult: Int): PictureOfTheDayFragment {
            val pictureOfTheDayFragment = PictureOfTheDayFragment()
            val bundle = Bundle()
            bundle.putInt("dayResult", dayResult)
            pictureOfTheDayFragment.arguments = bundle
            return pictureOfTheDayFragment
        }
    }
}