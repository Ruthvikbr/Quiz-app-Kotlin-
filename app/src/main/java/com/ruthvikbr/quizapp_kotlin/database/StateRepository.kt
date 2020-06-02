package com.ruthvikbr.quizapp_kotlin.database


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ruthvikbr.quizapp_kotlin.data.State

class StateRepository(private val application: Application ) {
    private val stateDao:StateDao

    init {
        val stateDatabase = StateDatabase.getInstance(application)
        stateDao = stateDatabase.stateDao
    }

    companion object{
        private var stateRepository:StateRepository?=null
        fun getRepository(application: Application): StateRepository? {
            if(stateRepository == null){
                synchronized(StateRepository::class.java){
                    if (stateRepository==null){
                        stateRepository = StateRepository(application)
                    }
                }
            }
            return stateRepository
        }
    }

     fun insertState( state:State){
        stateDao.insertState(state)
    }

     fun deletState( state:State){
        stateDao.deleteState(state)
    }
     fun updateState( state:State){
        stateDao.updateState(state)
    }

    fun getAllStates():LiveData<PagedList<State>>{
        val pageSize = 15
        return LivePagedListBuilder(stateDao.getAllStates(),pageSize).build()
    }
}