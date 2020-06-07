package com.ruthvikbr.quizapp_kotlin.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "State")
data class State(


    @ColumnInfo(name = "stateName")
    var stateName: String,

    @ColumnInfo(name = "capitalName")
    var capitalName: String,
    @PrimaryKey(autoGenerate = true)
    var StateID: Int = 0

) : Parcelable

