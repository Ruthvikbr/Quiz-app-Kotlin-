package com.ruthvikbr.quizapp_kotlin.ui.settings

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.ruthvikbr.quizapp_kotlin.R
import com.ruthvikbr.quizapp_kotlin.utils.NotificationWorker
import java.util.*
import java.util.concurrent.TimeUnit

class Settings : PreferenceFragmentCompat() {
    private val work = "Notification work"
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings,rootKey)
        val notificationPreference: SwitchPreference? = findPreference("Notification_preference")

        notificationPreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->

                val current: Long = System.currentTimeMillis()

                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR_OF_DAY, 19)
                calendar.set(Calendar.MINUTE, 15)

                if (calendar.timeInMillis < current) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                }

                val workManager: WorkManager = WorkManager.getInstance(requireActivity())
                val builder: PeriodicWorkRequest.Builder =
                    PeriodicWorkRequest.Builder(NotificationWorker::class.java, 1, TimeUnit.DAYS)
                builder.setInitialDelay(calendar.timeInMillis - current, TimeUnit.MILLISECONDS)

                val check: Boolean = newValue as Boolean

                if (check) {
                    workManager.enqueueUniquePeriodicWork(
                        work,
                        ExistingPeriodicWorkPolicy.REPLACE,
                        builder.build()
                    )
                } else {
                    workManager.cancelUniqueWork(work)
                }

                true
            }
    }

}


