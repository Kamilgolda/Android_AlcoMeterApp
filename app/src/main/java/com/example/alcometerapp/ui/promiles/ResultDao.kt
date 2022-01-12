package com.example.alcometerapp.ui.promiles

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.alcometerapp.ui.profile.Profile

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