package com.example.appifoc.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appifoc.entities.Patient.PatientMedication

@Dao
interface PatientMedicationDao {

    @Insert
    suspend fun insertPatientMedication(patientMedication: PatientMedication)

    @Query("SELECT * FROM patient_medications WHERE patientId = :patientId")
    suspend fun getMedicationsForPatient(patientId: Long): List<PatientMedication>

    @Query("DELETE FROM patient_medications WHERE patientId = :patientId AND medicationId = :medicationId")
    suspend fun removeMedicationFromPatient(patientId: Long, medicationId: Long)
}
