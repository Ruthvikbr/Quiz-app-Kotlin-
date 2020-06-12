package com.ruthvikbr.quizapp_kotlin.database

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ruthvikbr.quizapp_kotlin.data.State
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [State::class],version = 1,exportSchema = false)
abstract class StateDatabase:RoomDatabase() {

    abstract val stateDao: StateDao

    companion object {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()

        @Volatile
        private var INSTANCE: StateDatabase? = null

        fun getInstance(context: Context): StateDatabase {

            synchronized(this) {
                var instance: StateDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext
                        , StateDatabase::class.java
                        , "StateDatabase"
                    ).addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            executor.execute {
                                populateDB(context.assets, getInstance(context))
                            }
                        }
                    }).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }

        }




    fun populateDB(assetManager: AssetManager, stateDB: StateDatabase) {
        var jsonString: String ? = null
        try {
            jsonString = assetManager.open("state-capital.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

        val stateDao:StateDao = stateDB.stateDao
        try {
            val states = JSONObject(jsonString!!)
            Log.v("Json response",""+jsonString)
            val section: JSONObject = states.getJSONObject("sections")
            parseJSON(section.getJSONArray("States (A-L)"), stateDao)
            parseJSON(section.getJSONArray("States (M-Z)"), stateDao)
            parseJSON(section.getJSONArray("Union Territories"), stateDao)


        } catch (e: JSONException) {
            e.stackTrace
        }

    }

    private fun parseJSON(states: JSONArray, stateDao: StateDao) {
        try {
            for (i in 0 until states.length()) {
                val state: JSONObject = states.getJSONObject(i)
                val stateName: String = state.getString("key")
                val capitalName: String = state.getString("val")
                val newState = State(stateName, capitalName)
                stateDao.insertState(newState)
            }
        } catch (e: JSONException) {
            Log.v("Parsing error"," "+e.stackTrace)
        }
    }
}

}