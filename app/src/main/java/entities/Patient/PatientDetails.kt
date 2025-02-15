package com.example.appifoc.entities.patient

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Dettagli aggiuntivi per il paziente.
 */
@Entity(tableName = "patient_details")
data class PatientDetails(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID univoco per i dettagli
    val patientId: Long, // Riferimento al paziente
    val allergies: String? = null, // Allergie
    val medicalHistory: String? = null, // Storia medica
    val medications: String? = null, // Farmaci in uso
    val emergencyPlan: String? = null // Piano di emergenza (opzionale)
)
