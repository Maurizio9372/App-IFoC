package com.example.appifoc.entities.health

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index

/**
 * Classe per il monitoraggio dei parametri vitali del paziente.
 */
@Entity(
    tableName = "health_monitor",
    foreignKeys = [ForeignKey(
        entity = Patient::class,
        parentColumns = ["id"],
        childColumns = ["patientId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["patientId"])]
)
data class HealthMonitor(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID univoco per ogni monitoraggio
    val patientId: Long, // Riferimento al paziente
    val date: Long, // Data del monitoraggio (timestamp)
    val bloodPressureSystolic: Int?, // Pressione sistolica
    val bloodPressureDiastolic: Int?, // Pressione diastolica
    val glucose: Float?, // Glicemia
    val heartRate: Int?, // Frequenza cardiaca
    val weight: Float?, // Peso
    val note: String? = null, // Annotazio
