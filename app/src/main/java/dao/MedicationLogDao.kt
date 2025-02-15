package com.example.appifoc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appifoc.data.entities.MedicationLog
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationLogDao {

    @Insert
    suspend fun insertMedicationLog(medicationLog: MedicationLog)

    @Update
    suspend fun updateMedicationLog(medicationLog: MedicationLog)

    @Query("SELECT * FROM medication_log WHERE patientId = :patientId ORDER BY date DESC")
    fun getMedicationLogsForPatient(patientId: Long): Flow<List<MedicationLog>>

    @Query("SELECT * FROM medication_log WHERE medicationId = :medicationId ORDER BY date DESC")
    fun getMedicationLogsForMedication(medicationId: Long): Flow<List<MedicationLog>>

    @Query("SELECT * FROM medication_log WHERE completed = 0 ORDER BY date ASC")
    fun getIncompleteMedicationLogs(): Flow<List<MedicationLog>>
}
