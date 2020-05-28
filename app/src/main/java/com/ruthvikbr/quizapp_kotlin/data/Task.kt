package com.ruthvikbr.quizapp_kotlin.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Task")
data class Task (
    @PrimaryKey(autoGenerate = true)
    var ID:Int,

    @ColumnInfo(name = "taskName")
    var taskName:String,

    @ColumnInfo(name = "taskDescription")
    var taskDescription:String,

    @ColumnInfo(name = "priority")
    var priority:Int

)