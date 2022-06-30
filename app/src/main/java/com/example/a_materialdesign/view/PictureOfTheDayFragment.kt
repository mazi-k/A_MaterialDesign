package com.example.a_materialdesign.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.a_materialdesign.R
import com.example.a_materialdesign.databinding.FragmentPictureOfTheDayBinding
import com.example.a_materialdesign.viewmodel.AppState
import com.example.a_materialdesign.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.time.LocalDate

class PictureOfTheDayFragment : Fragment() {

    private var isMain = true
    private var result: Int = 0

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
        bottomSheetBehavior()

        binding.lifeHack.bottomSheetLine.setOnClickListener {
            bottomSheetBehavior()
        }
    }

    private fun bottomSheetBehavior() {
        val params =
            (binding.lifeHack.bottomSheetContainer.layoutParams as CoordinatorLayout.LayoutParams)
        val behavior = params.behavior as BottomSheetBehavior
        behavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        behavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    /*   BottomSheetBehavior.STATE_DRAGGING -> TODO("not implemented")
                       BottomSheetBehavior.STATE_COLLAPSED -> TODO("not implemented")
                       BottomSheetBehavior.STATE_EXPANDED -> TODO("not implemented")
                       BottomSheetBehavior.STATE_HALF_EXPANDED -> TODO("not implemented")
                       BottomSheetBehavior.STATE_HIDDEN -> TODO("not implemented")
                       BottomSheetBehavior.STATE_SETTLING -> TODO("not implemented")*/
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("@@@", "$slideOffset slideOffset")
            }

        })
    }

    private fun wikiSearch() {
        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.menu.menu_bottom_bar, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.app_bar_fav -> Toast.makeText(context, "Favourite", Toast.LENGTH_SHORT).show()
//            R.id.app_bar_settings -> requireActivity().supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.container, SettingsFragment.newInstance())
//                .addToBackStack(null)
//                .commit()
//            R.id.app_bar_telescope -> {
//                startActivity(Intent(requireContext(), ApiActivity::class.java))
//            }
//            android.R.id.home -> {
//                BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager, "")
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.imageView.setImageResource(R.drawable.ic_load_error_vector)
            }
            is AppState.Loading -> {
                binding.imageView.setImageResource(R.drawable.ic_no_photo_vector)
            }
            is AppState.Success -> {
                binding.lifeHack.title.text = appState.serverResponseData.title
                binding.lifeHack.explanation.text = appState.serverResponseData.explanation
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