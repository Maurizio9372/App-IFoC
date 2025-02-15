package com.example.appifoc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.appifoc.data.entities.NeedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NeedDao {

    @Insert
    suspend fun insertNeed(needEntity: NeedEntity)

    @Update
    suspend fun updateNeed(needEntity: NeedEntity)

    @Query("SELECT * FROM needs WHERE patientId = :patientId ORDER BY priority DESC")
    fun getNeedsByPatientId(patientId: Long): Flow<List<NeedEntity>>

    @Query("SELECT * FROM needs WHERE status = :status ORDER BY date DESC")
    fun getNeedsByStatus(status: String): Flow<List<NeedEntity>>

    @Query("SELECT * FROM needs ORDER BY date DESC")
    fun getAllNeeds(): Flow<List<NeedEntity>>
}
