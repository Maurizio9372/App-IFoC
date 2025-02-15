package com.example.appifoc.entities.need

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.appifoc.classifier.NeedType
import com.example.appifoc.entities.patient.Patient

/**
 * Rappresenta un bisogno associato a un paziente.
 */
@Entity(
    tableName = "needs", // Nome della tabella per i bisogni
    foreignKeys = [
        ForeignKey(
            entity = Patient::class, // Relazione con la tabella dei pazienti
            parentColumns = ["id"], // Colonna ID della tabella Patient
            childColumns = ["patientId"], // Colonna patientId nella tabella NeedEntity
            onDelete = ForeignKey.CASCADE // Se il paziente viene eliminato, elimina anche il bisogno
        )
    ],
    indices = [Index(value = ["patientId"])] // Ottimizzazione della ricerca per paziente
)
data class NeedEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID univoco
    val patientId: Long, // ID del paziente associato
    val type: NeedType,
