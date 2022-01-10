package com.example.alcometerapp.ui.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    var userId: Long = 0L,

    @ColumnInfo(name = "name")
    public var name : String,

    @ColumnInfo(name = "gender")
    var gender : String,

    @ColumnInfo(name = "growth")
    var growth : Int,

    @ColumnInfo(name = "weight")
    var weight : Int
)
