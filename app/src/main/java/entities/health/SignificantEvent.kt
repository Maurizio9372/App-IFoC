package com.example.appifoc.entities.health

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index

/**
 * Classe per registrare eventi significativi nella salute del paziente.
 */
@Entity(
    tableName = "significant_events",
    foreignKeys = [ForeignKey(
        entity = Patient::class,
        parentColumns = ["id"],
        childColumns = ["patientId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["patientId"])]
)
data class SignificantEvent(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID univoco per ogni evento
    val patientId: Long, // Riferimento al paziente
    val description: String, // Descrizione dell'evento
    val date: Long, // Data dell'evento (timestamp)
    val type: String? = null // Classificazione dell'evento (opzionale)
)
