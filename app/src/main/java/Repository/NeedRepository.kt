package com.example.appifoc.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appifoc.dao.NeedDao
import com.example.appifoc.entities.need.NeedEntity
import com.example.appifoc.entities.need.NeedStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NeedRepository(private val needDao: NeedDao) {

    val errorState: MutableLiveData<String> = MutableLiveData()

    suspend fun saveNeed(patientId: Long, description: String, type: NeedEntity.NeedType, priority: String) {
        try {
            val needEntity = NeedEntity(
                patientId = patientId,
                description = description,
                type = type,
                date = System.currentTimeMillis(),
                priority = priority
            )
            withContext(Dispatchers.IO) {
                needDao.insertNeed(needEntity)
            }
        } catch (e: Exception) {
            Log.e("NeedRepository", "Errore durante il salvataggio del bisogno: ${e.message}", e)
            errorState.postValue("Errore durante il salvataggio del bisogno: ${e.message}")
        }
    }

    fun getAllNeeds(): Flow<List<NeedEntity>> {
        return needDao.getAllNeeds()
    }

    fun getNeedsByPatient(patientId: Long): Flow<List<NeedEntity>> {
        return needDao.getNeedsByPatientId(patientId)
    }

    fun getNeedsByStatus(status: NeedStatus): Flow<List<NeedEntity>> {
        return needDao.getNeedsByStatus(status.name)
    }

    suspend fun updateNeedStatus(id: Long, newStatus: NeedStatus) {
        try {
            withContext(Dispatchers.IO) {
                needDao.updateNeedStatus(id, newStatus.name)
            }
        } catch (e: Exception) {
            Log.e("NeedRepository", "Errore durante l'aggiornamento dello stato del bisogno: ${e.message}", e)
            errorState.postValue("Errore durante l'aggiornamento dello stato del bisogno: ${e.message}")
        }
    }

    suspend fun deleteNeed(need: NeedEntity) {
        try {
            withContext(Dispatchers.IO) {
                needDao.deleteNeed(need)
            }
        } catch (e: Exception) {
            Log.e("NeedRepository", "Errore durante l'eliminazione del bisogno: ${e.message}", e)
            errorState.postValue("Errore durante l'eliminazione del bisogno: ${e.message}")
        }
    }
}
