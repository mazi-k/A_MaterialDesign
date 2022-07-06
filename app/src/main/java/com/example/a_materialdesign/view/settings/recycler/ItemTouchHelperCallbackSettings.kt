package com.example.a_materialdesign.view.settings.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallbackSettings(private val adapterCallback: ItemTouchHelperAdapter) :
ItemTouchHelper.Callback() {

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        start: RecyclerView.ViewHolder,
        end: RecyclerView.ViewHolder
    ): Boolean {
        adapterCallback.onItemMove(start.adapterPosition, end.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapterCallback.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is RecyclerFragmentAdapter.MarsViewHolder)
                viewHolder.onItemSelected()
            if (viewHolder is RecyclerFragmentAdapter.EarthViewHolder)
                viewHolder.onItemSelected()
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is RecyclerFragmentAdapter.MarsViewHolder)
            viewHolder.onItemClear()
        if (viewHolder is RecyclerFragmentAdapter.EarthViewHolder)
            viewHolder.onItemClear()
        super.clearView(recyclerView, viewHolder)
    }
}