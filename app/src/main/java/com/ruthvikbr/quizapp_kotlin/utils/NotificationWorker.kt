package com.ruthvikbr.quizapp_kotlin.utils

import android.app.Application
import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ruthvikbr.quizapp_kotlin.data.State
import com.ruthvikbr.quizapp_kotlin.database.StateRepository

class NotificationWorker(context: Context,workerParameters: WorkerParameters) : Worker(context,workerParameters) {
    private var stateRepository:StateRepository = StateRepository.getRepository(context.applicationContext as Application)!!
    private val notifications:Notifications = Notifications()

    override fun doWork(): Result {
        val randomState:State = stateRepository.getRandomState()
        notifications.getDailyNotification(applicationContext,randomState)
        return Result.success()
    }

}
