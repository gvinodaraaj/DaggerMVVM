package com.example.daggermvvm.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(Task: Task)

    @Delete
    suspend fun delete(Task: Task)

    @Update
    suspend fun update(Task: Task)

    @Query("select * from TaskTable order by id ASC")
    fun getAllNotes(): LiveData<List<Task>>

}