package com.example.a_materialdesign.view.settings.recycler

class RecyclerRepositoryImpl: RecyclerRepository {

    private val data = arrayListOf(
        Pair(Data(1, "Earth", type = TYPE_EARTH), false),
        Pair(Data(2, "Earth", type = TYPE_EARTH), false),
        Pair(Data(3, "Mars", "", type = TYPE_MARS), false),
        Pair(Data(4, "Earth", type = TYPE_EARTH), false),
        Pair(Data(5, "Earth", type = TYPE_EARTH), false),
        Pair(Data(6, "Earth", type = TYPE_EARTH), false),
        Pair(Data(7, "Mars", null, type = TYPE_MARS), false)
    )

    override fun getData(): ArrayList<Pair<Data, Boolean>> {
        return data
    }

    override fun deleteItem(position: Int) {
        data.remove(data[position])
    }

    override fun addItem(item: Pair<Data, Boolean>) {
        data.add(item)
    }

    override fun updateItem(position: Int, item: Pair<Data, Boolean>) {
        data[position] = item
    }

}