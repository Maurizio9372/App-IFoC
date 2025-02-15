package com.example.appifoc.repository

import com.example.appifoc.dao.PatientDao
import entities.Patient.Patient
import kotlinx.coroutines.flow.Flow

class PatientRepository(private val patientDao: PatientDao) {

    // Funzione per ottenere tutti i pazienti in tempo reale
    fun getAllPatients(): Flow<List<Patient>> {
        return patientDao.getAllPatients()  // Restituisci un Flow
    }

    // Funzione per aggiungere un paziente
    suspend fun addPatient(patient: Patient) {
        patientDao.insertPatient(patient)
    }

    // Funzione per aggiornare un paziente
    suspend fun updatePatient(patient: Patient) {
        patientDao.updatePatient(patient)
    }

    // Funzione per eliminare un paziente
    suspend fun deletePatient(patient: Patient) {
        patientDao.deletePatient(patient)
    }
}
