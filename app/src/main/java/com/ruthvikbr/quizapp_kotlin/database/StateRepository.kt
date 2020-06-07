package com.ruthvikbr.quizapp_kotlin.database


import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ruthvikbr.quizapp_kotlin.data.State
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class StateRepository(application: Application ) {
    private val stateDao:StateDao
    private val executor:ExecutorService = Executors.newSingleThreadExecutor()

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
        executor.execute {
            stateDao.insertState(state)
        }

    }

     fun deleteState( state:State){
         executor.execute {
             stateDao.deleteState(state)
         }
    }
     fun updateState( state:State){
         executor.execute {
             stateDao.updateState(state)
         }
    }

    fun getAllStates():LiveData<PagedList<State>>{
        val pageSize = 15
        return LivePagedListBuilder(stateDao.getAllStates(),pageSize).build()
    }
}