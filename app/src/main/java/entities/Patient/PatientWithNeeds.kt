package com.example.appifoc.entities.patient

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.appifoc.entities.need.NeedEntity
import com.example.appifoc.entities.medication.PatientMedication

/**
 * Rappresenta un paziente e i suoi bisogni e farmaci associati.
 */
data class PatientWithNeeds(
    @Embedded val patient: Patient, // Paziente
    @Relation(
        parentColumn = "id", // Colonna ID del paziente
        entityColumn = "patientId", // Colonna patientId nella tabella dei bisogni
        entity = NeedEntity::class // Entità dei bisogni
    )
    val needs: List<NeedEntity>, // Lista dei bisogni del paziente

    @Relation(
        parentColumn = "id", // Colonna ID del paziente
        entityColumn = "patientId", // Colonna patientId nella tabella PatientMedication
        entity = PatientMedication::class // Entità dei farmaci
    )
    val medications: List<PatientMedication> // Lista dei farmaci del paziente
)
