package com.example.alcometerapp.ui.promiles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class Result(
    @PrimaryKey(autoGenerate = true)
    var resultId: Long = 0L,

    @ColumnInfo(name = "userId")
    var userId: Long
)
