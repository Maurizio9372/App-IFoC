package com.example.appifoc.workers

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.appifoc.database.AppDatabase
import entities.Medication.Medication

class MedicationReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val medicationId = inputData.getLong("medicationId", 0)
        val patientId = inputData.getLong("patientId", 0)

        // Ottieni il farmaco dal database
        val medication = getMedicationFromDatabase(medicationId)

        // Crea la notifica
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext, "MedicationReminder")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setContentTitle("Assumi il farmaco: ${medication.name}")
            .setContentText("È il momento di prendere il tuo farmaco!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        // Mostra la notifica
        notificationManager.notify(patientId.toInt(), notification)

        return Result.success()
    }

    private fun getMedicationFromDatabase(medicationId: Long): Medication {
        val db = AppDatabase.getDatabase(applicationContext)
        return db.medicationDao().getMedicationById(medicationId)
    }
}
