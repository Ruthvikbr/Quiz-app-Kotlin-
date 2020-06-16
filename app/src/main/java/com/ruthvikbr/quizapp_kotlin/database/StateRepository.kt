package com.ruthvikbr.quizapp_kotlin.database


import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.ruthvikbr.quizapp_kotlin.data.State
import kotlinx.coroutines.*
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class StateRepository(application: Application) {
    private val stateDao: StateDao
    private val executor: ExecutorService = Executors.newSingleThreadExecutor()


    init {
        val stateDatabase = StateDatabase.getInstance(application)
        stateDao = stateDatabase.stateDao
    }

    companion object {
        private var stateRepository: StateRepository? = null
        fun getRepository(application: Application): StateRepository? {
            if (stateRepository == null) {
                synchronized(StateRepository::class.java) {
                    if (stateRepository == null) {
                        stateRepository = StateRepository(application)
                    }
                }
            }
            return stateRepository
        }
    }

    fun insertState(state: State) {
        executor.execute {
            stateDao.insertState(state)
        }

    }

    fun deleteState(state: State) {
        executor.execute {
            stateDao.deleteState(state)
        }
    }

    fun updateState(state: State) {
        executor.execute {
            stateDao.updateState(state)
        }
    }

    fun getAllStates(): LiveData<PagedList<State>> {
        val pageSize = 15
        return LivePagedListBuilder(stateDao.getAllStates(), pageSize).build()
    }

    fun getQuizStates(): Future<List<State>>?{
        val callable:Callable<List<State>> = Callable {
            return@Callable stateDao.getQuizStates()
        }
        return executor.submit(callable)
    }

    @WorkerThread
    fun getRandomState():State{
        return stateDao.getRandomState()
    }

    fun getStatesInSortedOrder(sortOrder:String): LiveData<PagedList<State>> {
        val pageSize = 15
        return LivePagedListBuilder(stateDao.getStatesInSortedOrder(constructQuery(sortOrder)), pageSize).build()
    }

    private fun constructQuery(sortBy:String):SupportSQLiteQuery{
        val s = "SELECT * FROM State ORDER BY $sortBy ASC"
        return SimpleSQLiteQuery(s)
    }


}
