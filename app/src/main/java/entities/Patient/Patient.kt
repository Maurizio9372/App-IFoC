package com.example.appifoc.entities.patient

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Embedded

/**
 * Classe che rappresenta un paziente.
 */
@Entity(
    tableName = "patients",
    indices = [Index(value = ["name"])] // Ottimizzazione per ricerche rapide
)
data class Patient(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID univoco generato automaticamente
    val name: String, // Nome del paziente
    val age: Int, // Età del paziente
    val gender: String?, // Sesso (opzionale)
    val diagnosis: String?, // Diagnosi principale (opzionale)
    val contactInfo: String? = null, // Informazioni di contatto (opzionale)
    val emergencyContact: String? = null, // Contatto di emergenza (opzionale)

    @Embedded
    val details: PatientDetails? // Dettagli aggiuntivi del paziente
)
