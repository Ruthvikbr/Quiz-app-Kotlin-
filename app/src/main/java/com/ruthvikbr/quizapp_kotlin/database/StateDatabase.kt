package com.ruthvikbr.quizapp_kotlin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ruthvikbr.quizapp_kotlin.data.State
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [State::class],version = 1,exportSchema = false)
abstract class StateDatabase:RoomDatabase() {

    val executor:ExecutorService = Executors.newSingleThreadExecutor()
    abstract val stateDao:StateDao

    companion object{

        @Volatile
        private var INSTANCE:StateDatabase ?= null

        fun getInstance(context: Context):StateDatabase{

            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext
                        ,StateDatabase::class.java
                        ,"StateDatabase"
                    ).addCallback(object :RoomDatabase.Callback(){
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)


                        }
                    }).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}