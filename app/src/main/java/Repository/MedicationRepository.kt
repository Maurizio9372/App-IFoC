package com.example.appifoc.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appifoc.dao.MedicationDao
import com.example.appifoc.entities.medication.Medication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MedicationRepository(private val medicationDao: MedicationDao) {

    val errorState: MutableLiveData<String> = MutableLiveData()

    fun getAllMedications(): Flow<List<Medication>> {
        return medicationDao.getAllMedications()
    }

    suspend fun addMedication(medication: Medication) {
        try {
            withContext(Dispatchers.IO) {
                medicationDao.insert(medication)
            }
        } catch (e: Exception) {
            Log.e("MedicationRepository", "Errore durante l'aggiunta del farmaco: ${e.message}", e)
            errorState.postValue("Errore durante l'aggiunta del farmaco: ${e.message}")
        }
    }

    suspend fun updateMedication(medication: Medication) {
        try {
            withContext(Dispatchers.IO) {
                medicationDao.update(medication)
            }
        } catch (e: Exception) {
            Log.e("MedicationRepository", "Errore durante l'aggiornamento del farmaco: ${e.message}", e)
            errorState.postValue("Errore durante l'aggiornamento del farmaco: ${e.message}")
        }
    }

    suspend fun deleteMedication(medication: Medication) {
        try {
            withContext(Dispatchers.IO) {
                medicationDao.delete(medication)
            }
        } catch (e: Exception) {
            Log.e("MedicationRepository", "Errore durante l'eliminazione del farmaco: ${e.message}", e)
            errorState.postValue("Errore durante l'eliminazione del farmaco: ${e.message}")
        }
    }
}
