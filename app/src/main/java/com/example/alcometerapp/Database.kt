package com.example.alcometerapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.alcometerapp.ui.profile.Profile
import com.example.alcometerapp.ui.promiles.Result
import com.example.alcometerapp.ui.profile.ProfileDao
import com.example.alcometerapp.ui.promiles.ResultDao

@Database(entities = [Profile::class, Result::class ], version =5)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {

    abstract fun profileDao(): ProfileDao
    abstract fun resultDao(): ResultDao

    companion object{
        val DATABASE_NAME: String = "alco_meter_db"
    }
}