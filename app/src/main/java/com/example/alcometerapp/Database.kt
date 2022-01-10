package com.example.alcometerapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alcometerapp.ui.profile.Profile
import com.example.alcometerapp.ui.profile.ProfileDao

@Database(entities = [Profile::class ], version =2)
abstract class Database: RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    companion object{
        val DATABASE_NAME: String = "alco_meter_db"
    }
}