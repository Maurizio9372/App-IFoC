package com.example.appifoc.entities.need

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "need_categories")
data class NeedCategory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID univoco per ogni categoria
    val name: String, // Nome della categoria di bisogno (e.g., Sanitari, Sociali, Educativi)
    val description: String? = null // Descrizione opzionale della categoria
)
