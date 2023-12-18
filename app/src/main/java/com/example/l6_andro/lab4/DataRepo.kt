package com.example.l6_andro.lab4

class DataRepo {
    private val LIST_SIZE = 2
    private lateinit var dataList: MutableList<DataItem>

    companion object{
        private var INSTANCE: DataRepo? = null
        fun getInstance(): DataRepo {
            if(INSTANCE == null){
                INSTANCE = DataRepo()
            }

            return INSTANCE!!
        }
    }

    fun getData() : MutableList<DataItem> {
        return dataList
    }

    fun deleteItem(position: Int): Boolean {
        dataList.removeAt(position)
        return true
    }

    fun addItem(item: DataItem): Boolean {
        return dataList.add(item)
    }

    init {
        dataList = MutableList(LIST_SIZE) { i -> DataItem(i) }
        //dataList.add(DataItem(16))
    }
}