package com.example.a_materialdesign.view.settings.recycler

import androidx.lifecycle.ViewModel

class RecycleViewModel(
) : ViewModel() {

    private val repository = RecyclerRepositoryImpl()

    fun getData() = repository.getData()

    fun deleteItem(position: Int) {
        repository.deleteItem(position)
    }

    fun addItem(item: Pair<Data, Boolean>) {
        repository.addItem(item)
    }

    fun updateItem(position: Int, item: Pair<Data, Boolean>) {
        repository.updateItem(position, item)
    }
}