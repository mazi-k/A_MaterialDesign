package com.example.a_materialdesign.view.settings.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.a_materialdesign.databinding.FragmentRecyclerViewBinding

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding: FragmentRecyclerViewBinding
        get() {
            return _binding!!
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

        val data = arrayListOf(
            Pair(Data(1, "Earth", type = TYPE_EARTH), false),
            Pair(Data(2, "Earth", type = TYPE_EARTH), false),
            Pair(Data(3, "Mars", "", type = TYPE_MARS), false),
            Pair(Data(4, "Earth", type = TYPE_EARTH), false),
            Pair(Data(5, "Earth", type = TYPE_EARTH), false),
            Pair(Data(6, "Earth", type = TYPE_EARTH), false),
            Pair(Data(7, "Mars", null, type = TYPE_MARS), false)
        )
        data.add(0, Pair(Data(0, "Заголовок", type = TYPE_HEADER), false))

        adapter.setData(data)

        ItemTouchHelper(ItemTouchHelperCallbackSettings(adapter)).attachToRecyclerView(binding.recyclerView)

    }
}