package com.example.appifoc.entities.medication

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "medication_log",
    foreignKeys = [
        ForeignKey(
            entity = Medication::class,
            parentColumns = ["id"],
            childColumns = ["medicationId"],
            onDelete = ForeignKey.CASCADE // Cancella il log se il farmaco viene eliminato
        )
    ],
    indices = [Index(value = ["medicationId"])] // Ottimizza la ricerca dei log
)
data class MedicationLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID univoco log
    val patientId: Long, // Paziente associato
    val medicationId: Long, // Farmaco assunto
    val date: Long, // Data di assunzione (timestamp)
    val doseTaken: Float, // Dose assunta
    val completed: Boolean = false, // Assunzione completata?
    val note: String? = null // Note aggiuntive
)
