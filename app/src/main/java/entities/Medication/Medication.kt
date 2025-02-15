package com.example.appifoc.entities.medication

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "medications",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE // Cancella il farmaco se il paziente è eliminato
        )
    ],
    indices = [Index(value = ["name"])] // Ottimizza la ricerca per nome farmaco
)
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID farmaco
    val patientId: Long, // Paziente associato
    val name: String, // Nome del farmaco
    val dosage: Float, // Dosaggio (es. 10.0 per 10 mg)
    val unit: String? = null, // Unità di misura (mg, ml, ecc.)
    val sideEffects: String? = null, // Effetti collaterali
    val schedule: String? = null, // Orario di assunzione
    val active: Boolean = true, // Se il farmaco è attivo
    val tags: String? = null, // Etichette o categorie
    val expiryDate: Long? = null // Data di scadenza (timestamp)
)
