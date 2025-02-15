package com.example.appifoc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.appifoc.data.entities.SignificantEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface SignificantEventDao {

    @Insert
    suspend fun insert(event: SignificantEvent)

    @Query("SELECT * FROM significant_events WHERE patientId = :patientId ORDER BY date DESC")
    fun getEventsByPatientId(patientId: Long): Flow<List<SignificantEvent>>

    @Query("SELECT * FROM significant_events ORDER BY date DESC")
    fun getAllEvents(): Flow<List<SignificantEvent>>
}
