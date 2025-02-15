package com.example.appifoc.entities.medication

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "prescriptions",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE // Cancella la prescrizione se il paziente viene eliminato
        )
    ],
    indices = [Index(value = ["patientId"])] // Ottimizza la ricerca delle prescrizioni per paziente
)
data class Prescription(
    @PrimaryKey(autoGenerate = true) val prescriptionId: Long = 0, // ID della prescrizione
    val patientId: Long, // ID del paziente
    val medicationName: String, // Nome del farmaco
    val dosage: String, // Posologia (es. "10 mg", "1 compressa")
    val startDate: Long, // Data di inizio della prescrizione (timestamp)
    val endDate: Long // Data di fine prescrizione (timestamp)
)
