package com.example.exampleeee.data

import android.app.Application

class InventoryApplication: Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this)}
}