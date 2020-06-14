package com.ruthvikbr.quizapp_kotlin.database

import androidx.paging.DataSource
import androidx.room.*
import com.ruthvikbr.quizapp_kotlin.data.State


@Dao
interface StateDao {

    @Insert
    fun insertState(state: State)

    @Update
    fun updateState(state: State)

    @Delete
    fun deleteState(state: State)

    @Query("SELECT * FROM State")
    fun getAllStates(): DataSource.Factory<Int, State>

    @Query("SELECT * FROM State ORDER BY RANDOM() LIMIT 4")
     fun getQuizStates(): List<State>

    @Query("SELECT * FROM State ORDER BY RANDOM() LIMIT 1")
    fun getRandomState(): State
}