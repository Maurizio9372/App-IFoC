package com.example.appifoc.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.appifoc.R

class NotificationWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val message = inputData.getString("message") ?: "Notifica senza messaggio"
        sendNotification(message)
        return Result.success()
    }

    private fun sendNotification(message: String) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Crea il canale di notifica (necessario per Android Oreo e versioni successive)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "default_channel",
                "Promemoria",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        // Crea la notifica
        val notification = NotificationCompat.Builder(applicationContext, "default_channel")
            .setContentTitle("Promemoria")
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_notification)  // Usa l'icona personalizzata
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        // Invia la notifica
        notificationManager.notify(1, notification)
    }
}
