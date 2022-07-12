package com.example.a_materialdesign.view.settings.recycler

interface RecyclerRepository {
    fun getData(): ArrayList<Pair<Data, Boolean>>
    fun deleteItem(position: Int)
    fun addItem(item: Pair<Data, Boolean>)
    fun updateItem(position: Int, item: Pair<Data, Boolean>)
}