package com.example.appifoc.utils

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.appifoc.workers.NotificationWorker
import java.util.concurrent.TimeUnit

object NotificationHelper {
    fun scheduleNotification(context: Context, delayInMinutes: Long, message: String) {
        val data = Data.Builder()
            .putString("message", message)
            .build()

        val notificationWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInputData(data)
            .setInitialDelay(delayInMinutes, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(context).enqueue(notificationWorkRequest)
    }
}

