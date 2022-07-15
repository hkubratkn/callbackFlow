package com.example.exampleeee.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forageable_database")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "name")
    var itemName: String,
    @ColumnInfo(name = "address")
    var itemAddress: String,
    @ColumnInfo(name = "in_season")
    var itemInSeason: Boolean,
    @ColumnInfo(name = "notes")
    var itemNotes: String
)