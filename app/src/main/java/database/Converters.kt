package com.example.appifoc.database

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    // Conversione da Date a Long (timestamp)
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    // Conversione da Long a Date (timestamp a oggetto Date)
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    // Conversione da List<String> a String (separato da virgola)
    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return list?.joinToString(",")
    }

    // Conversione da String a List<String> (separato da virgola)
    @TypeConverter
    fun toStringList(data: String?): List<String>? {
        return data?.split(",")?.map { it.trim() }
    }
}
