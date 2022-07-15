package com.example.exampleeee.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ItemDao {

    @Query("SELECT * FROM forageable_database")
    fun getForegeables():  Flow<List<Item>>

    @Query("SELECT * FROM forageable_database WHERE id = :id")
    fun getForegeable(id: Long): Flow<Item>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

}