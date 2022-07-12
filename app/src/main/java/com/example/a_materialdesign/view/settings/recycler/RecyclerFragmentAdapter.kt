package com.example.a_materialdesign.view.settings.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.a_materialdesign.R
import com.example.a_materialdesign.databinding.FragmentRecyclerItemEarthBinding
import com.example.a_materialdesign.databinding.FragmentRecyclerItemHeaderBinding
import com.example.a_materialdesign.databinding.FragmentRecyclerItemMarsBinding


const val TYPE_EARTH = 0
const val TYPE_MARS = 1
const val TYPE_HEADER = 2

class RecyclerFragmentAdapter :
    RecyclerView.Adapter<RecyclerFragmentAdapter.BaseViewHolder>(),
    ItemTouchHelperAdapter {

    private var dataList: ArrayList<Pair<Data,Boolean>> = arrayListOf()

    fun setData(newData: ArrayList<Pair<Data,Boolean>>) {
        this.dataList = newData
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].first.type
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        return when (viewType) {
            TYPE_EARTH -> {
                val binding =
                    FragmentRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(binding.root)
            }
            TYPE_MARS -> {
                val binding =
                    FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(binding.root)
            }
            TYPE_HEADER -> {
                val binding =
                    FragmentRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(binding)
            }
            else -> {
                val binding =
                    FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(binding.root)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class HeaderViewHolder(val binding: FragmentRecyclerItemHeaderBinding) :
        BaseViewHolder(binding.root) {
        override fun bind(data: Pair<Data,Boolean>) {
            binding.name.text = data.first.name
        }
    }

    class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Data,Boolean>) {
            val binding = FragmentRecyclerItemEarthBinding.bind(itemView)
            binding.name.text = data.first.name
        }
    }

    class MarsViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Data,Boolean>) {
            val binding = FragmentRecyclerItemMarsBinding.bind(itemView)
            binding.name.text = data.first.name
        }
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view), ItemTouchHelperViewHolder {
        abstract fun bind(data: Pair<Data,Boolean>)

        override fun onItemSelected() {
            itemView.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.colorAccent40Alfa
                )
            )
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition != 0 && toPosition != 0) {
            dataList.removeAt(fromPosition).apply {
                dataList.add(toPosition , this)
            }
            notifyItemMoved(fromPosition,toPosition)
        }
    }

    override fun onItemDismiss(position: Int) {
        if (position != 0) {
            dataList.removeAt(position)
            notifyItemRemoved(position)
        } else {
            notifyItemChanged(position)
        }
    }
}