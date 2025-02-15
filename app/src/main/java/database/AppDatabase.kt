package com.example.appifoc.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appifoc.dao.NeedDao
import com.example.appifoc.dao.MedicationDao
import com.example.appifoc.dao.MedicationLogDao
import com.example.appifoc.entities.need.NeedEntity
import com.example.appifoc.entities.medication.Medication
import com.example.appifoc.entities.medication.MedicationLog

@Database(
    entities = [NeedEntity::class, Medication::class, MedicationLog::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class) // Se hai converter personalizzati
abstract class AppDatabase : RoomDatabase() {
    abstract fun needDao(): NeedDao
    abstract fun medicationDao(): MedicationDao
    abstract fun medicationLogDao(): MedicationLogDao
}
