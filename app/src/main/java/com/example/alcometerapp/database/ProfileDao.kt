package com.example.alcometerapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.example.alcometerapp.database.Profile

@Dao
interface ProfileDao {
    @Update
    fun update(profile: Profile)

    @Query("SELECT * FROM profiles ORDER BY userId DESC LIMIT 1")
    fun get(): Profile

    @Insert(onConflict = REPLACE)
    fun insert(profile: Profile)
}