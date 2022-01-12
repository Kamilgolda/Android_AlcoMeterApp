package com.example.alcometerapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(result: Result)

    @Query("SELECT * FROM results ORDER BY resultId DESC LIMIT 1")
    fun get(): Result

    @Update
    fun update(result: Result)

    @Query("SELECT * FROM results ORDER BY resultId DESC")
    fun getAll(): LiveData<List<Result>>

    @Delete
    fun delete(result: Result)
}