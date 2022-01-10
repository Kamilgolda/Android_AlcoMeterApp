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
//    @Insert
//    fun insert(user: User)
//
    @Update
    fun update(profile: Profile)
//
//    @Query("SELECT * from users_table WHERE userId = :key")
//    fun get(key: Long): User?
//
//    //@Delete jak usuwamy jeden alement lub musimy miec liste elementów do usuniecia
//    @Query("DELETE FROM users_table")
//    fun clear()
//
    @Query("SELECT * FROM profiles ORDER BY userId DESC LIMIT 1")
    fun get(): Profile
//
//    @Query("SELECT * FROM users_table ORDER BY userId DESC")
//    fun getAllNights(): LiveData<List<User>>

    @Insert(onConflict = REPLACE)
    fun insert(profile: Profile)

//    @Query("SELECT * FROM users WHERE userId = :userId")
//    fun load(userId : String): Flow<User>
}