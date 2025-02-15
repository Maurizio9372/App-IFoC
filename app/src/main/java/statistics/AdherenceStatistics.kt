package com.example.appifoc.statistics

import com.example.appifoc.dao.MedicationLogDao
import entities.Medication.MedicationLog

class AdherenceStatistics(private val medicationLogDao: MedicationLogDao) {

    // Metodo per calcolare l'adesione del paziente
    fun calculateAdherence(patientId: Long): Float {
        val logs: List<MedicationLog> = medicationLogDao.getLogsForPatient(patientId)
        var totalScheduled = 0
        var totalTaken = 0

        for (log in logs) {
            totalScheduled++
            if (log.isTaken) {
                totalTaken++
            }
        }

        return if (totalScheduled == 0) 0f else (totalTaken.toFloat() / totalScheduled.toFloat()) * 100f
    }
}
