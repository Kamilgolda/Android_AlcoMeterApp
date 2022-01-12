package com.example.alcometerapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Update
    fun update(profile: Profile)

//    @Query("SELECT * from profiles WHERE userId = :key")
//    fun get(key: Long): User?
//


    @Query("SELECT * FROM profiles ORDER BY userId DESC LIMIT 1")
    fun get(): Profile

    @Insert(onConflict = REPLACE)
    fun insert(profile: Profile)
}