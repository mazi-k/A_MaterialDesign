package com.example.a_materialdesign.view.settings.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.a_materialdesign.databinding.FragmentRecyclerViewBinding

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding: FragmentRecyclerViewBinding
        get() {
            return _binding!!
        }

    val viewModel: RecycleViewModel by lazy {
        ViewModelProvider(this).get(RecycleViewModel::class.java)
    }

    private val adapter = RecyclerFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = adapter
        val data = viewModel.getData()

        data.add(0, Pair(Data(0, "Заголовок", type = TYPE_HEADER), false))

        adapter.setData(data)

        ItemTouchHelper(ItemTouchHelperCallbackSettings(adapter)).attachToRecyclerView(binding.recyclerView)
    }
}