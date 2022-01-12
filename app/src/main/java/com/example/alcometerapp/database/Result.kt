package com.example.alcometerapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "results")
data class Result(
    @PrimaryKey(autoGenerate = true)
    var resultId: Long = 0L,

    @ColumnInfo(name = "userId")
    var userId: Long,

    @ColumnInfo(name = "strength")
    var strength: Int,

    @ColumnInfo(name = "portion")
    var portion: Int,

    @ColumnInfo(name = "quantity")
    var quantity: Int,

    @ColumnInfo(name = "startDate")
    var startDate: Date,

    @ColumnInfo(name = "endDate")
    var endDate: Date
)
