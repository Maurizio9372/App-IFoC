package com.example.appifoc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appifoc.data.entities.Medication
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationDao {

    @Insert
    suspend fun insert(medication: Medication)

    @Update
    suspend fun updateMedication(medication: Medication)

    @Query("SELECT * FROM medications WHERE patientId = :patientId ORDER BY name ASC")
    fun getMedicationsByPatientId(patientId: Long): Flow<List<Medication>>

    @Query("SELECT * FROM medications ORDER BY name ASC")
    fun getAllMedications(): Flow<List<Medication>>
}
