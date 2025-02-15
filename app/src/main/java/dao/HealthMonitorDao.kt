package com.example.appifoc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appifoc.data.entities.HealthMonitor
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthMonitorDao {

    @Insert
    suspend fun insert(healthMonitor: HealthMonitor)

    @Query("SELECT * FROM health_monitor WHERE patientId = :patientId ORDER BY date DESC")
    fun getHealthDataByPatientId(patientId: Long): Flow<List<HealthMonitor>>

    @Query("SELECT * FROM health_monitor ORDER BY date DESC")
    fun getAllHealthData(): Flow<List<HealthMonitor>>
}

