package com.example.appifoc.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appifoc.data.entities.Patient
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Insert
    suspend fun insert(patient: Patient)

    @Update
    suspend fun update(patient: Patient)

    @Delete
    suspend fun delete(patient: Patient)

    @Query("SELECT * FROM patients WHERE id = :id")
    suspend fun getPatientById(id: Long): Patient?

    @Query("SELECT * FROM patients ORDER BY lastName ASC")
    fun getAllPatients(): Flow<List<Patient>>
}
