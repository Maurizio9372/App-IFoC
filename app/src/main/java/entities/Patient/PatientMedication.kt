package com.example.appifoc.entities.medication

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index
import com.example.appifoc.entities.patient.Patient

/**
 * Rappresenta il legame tra un paziente e i farmaci che sta assumendo.
 */
@Entity(
    tableName = "patient_medications",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class, // Relazione con la tabella dei pazienti
            parentColumns = ["id"], // Colonna ID nella tabella Patient
            childColumns = ["patientId"], // Colonna patientId nella tabella PatientMedication
            onDelete = ForeignKey.CASCADE // Se il paziente è eliminato, elimina anche il legame
        ),
        ForeignKey(
            entity = Medication::class, // Relazione con la tabella dei farmaci
            parentColumns = ["id"], // Colonna ID nella tabella Medication
            childColumns = ["medicationId"], // Colonna medicationId nella tabella PatientMedication
            onDelete = ForeignKey.CASCADE // Se il farmaco è eliminato, elimina anche il legame
        )
    ],
    indices = [Index(value = ["patientId"]), Index(value = ["medicationId"])] // Ottimizzazione delle ricerche
)
data class PatientMedication(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID univoco per il legame
    val patientId: Long, // ID del paziente
    val medicationId: Long, // ID del farmaco
    val startDate: Long, // Data di inizio dell'assunzione
    val endDate: Long?, // Data di fine dell'assunzione (può essere nulla)
    val dosage: String, // Dosaggio (es. "1 compressa")
    val instructions: String? = null // Istruzioni aggiuntive per l'assunzione
)
