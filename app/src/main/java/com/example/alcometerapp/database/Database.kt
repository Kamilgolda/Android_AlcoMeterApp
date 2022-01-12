package com.example.alcometerapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.alcometerapp.database.Profile
import com.example.alcometerapp.database.Result
import com.example.alcometerapp.database.ProfileDao
import com.example.alcometerapp.database.ResultDao
import com.example.alcometerapp.utils.Converters

@Database(entities = [Profile::class, Result::class ], version =7)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {

    abstract fun profileDao(): ProfileDao
    abstract fun resultDao(): ResultDao

    companion object{
        val DATABASE_NAME: String = "alco_meter_db"
    }
}