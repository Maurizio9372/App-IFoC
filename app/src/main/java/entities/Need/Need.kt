package com.example.appifoc.entities.need

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.appifoc.classifier.NeedType

/**
 * Classe che rappresenta un bisogno di un paziente.
 */
@Entity(
    tableName = "needs", // Nome della tabella per i bisogni
    foreignKeys = [
        ForeignKey(
            entity = NeedCategory::class, // Relazione con la tabella delle categorie di bisogno
            parentColumns = ["id"], // Colonna ID della tabella NeedCategory
            childColumns = ["categoryId"], // Colonna categoryId nella tabella Need
            onDelete = ForeignKey.SET_NULL // Se la categoria viene eliminata, il bisogno rimane senza categoria
        )
    ],
    indices = [Index(value = ["patientId"]), Index(value = ["categoryId"])] // Indici per ottimizzare le query
)
data class Need(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,  // ID univoco auto-generato
    val patientId: Long,  // ID del paziente associato a questo bisogno
    val categoryId: Long?, // ID della categoria del bisogno, può essere nullo
    val type: NeedType, // Tipo del bisogno (Sanitario, Sociale, Educativo)
    val description: String, // Descrizione del bisogno
    val date: Long, // Data di registrazione (timestamp in millisecondi)
    val priority: String = "medium", // Priorità del bisogno (low, medium, high)
    val status: NeedStatus = NeedStatus.OPEN // Stato del bisogno (OPEN, IN_PROGRESS, COMPLETED)
)
