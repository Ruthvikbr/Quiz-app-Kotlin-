package com.ruthvikbr.quizapp_kotlin.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "State")
data class State (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "StateID")
    var StateID:Int,

    @ColumnInfo(name = "stateName")
    var stateName:String,

    @ColumnInfo(name = "capitalName")
    var capitalName:String

)