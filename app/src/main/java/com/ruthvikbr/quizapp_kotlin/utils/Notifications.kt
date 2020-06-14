package com.ruthvikbr.quizapp_kotlin.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import com.ruthvikbr.quizapp_kotlin.R
import com.ruthvikbr.quizapp_kotlin.data.State
import com.ruthvikbr.quizapp_kotlin.ui.quiz.MainActivity

class Notifications {

    private val primaryChannelId: String = "primary_channel_id"
    private val notificationId: Int = 1

     fun getDailyNotification(context: Context, state: State) {

        val notificationManager: NotificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                primaryChannelId,
                "Daily Quiz",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.CYAN
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationTitle = "Daily Quiz"
        val notificationContent = "What is capital of state " + state.stateName + " ?"

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, primaryChannelId)
                .setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_baseline_settings_24)


        notificationManager.notify(notificationId, builder.build())
    }


}