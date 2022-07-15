package com.example.exampleeee.ui.screen.inventory

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.example.exampleeee.data.Item
import com.example.exampleeee.data.ItemDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ForageableViewModel(private val itemDao: ItemDao): ViewModel() {

    val allItems: Flow<List<Item>> = itemDao.getForegeables()
    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }
    fun getForegeable(id: Long): Flow<Item> {
        return itemDao.getForegeable(id)
    }
    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }

    fun isEntryValid( itemName: String, itemAddress: String, itemNotes: String): Boolean {
        if (itemName.isBlank() || itemAddress.isBlank() || itemNotes.isBlank()) {
            return false
        }
        return true
    }

    fun addNewItem(itemName: String, itemAddress: String, itemNotes: String) {
        val newItem = getNewItemEntry(itemName = itemName, itemAddress =  itemAddress, itemNotes = itemNotes)
        insertItem(newItem)
    }
    private fun getNewItemEntry(itemName: String, itemAddress: String, itemNotes: String): Item {
        return Item(
            itemName = itemName,
            itemAddress = itemAddress,
            itemInSeason = false,
            itemNotes = itemNotes
        )
    }
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }


/**
    fun updateItem(
        itemId: Int,
        itemName: String,
        itemPrice: String,
        itemCount: String
    ){
        val updatedItem = getUpdatedItemEntry(itemId, itemName, itemPrice, itemCount)
        updateItem(updatedItem)
    }


    private fun getUpdatedItemEntry(
        itemId: Int,
        itemName: String,
        itemPrice: String,
        itemCount: String
    ): Item {
        return Item(
            id = itemId,
            itemName = itemName,
            itemPrice = itemPrice.toDouble(),
            quantityInStock = itemCount.toInt()
        )
    }
*/
}

class InventoryViewModelFactory(
    private val itemDao: ItemDao
): ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T = ForageableViewModel(itemDao) as T
}