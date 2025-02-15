package com.example.appifoc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appifoc.repository.PatientRepository
import entities.Patient.Patient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PatientViewModel(private val patientRepository: PatientRepository) : ViewModel() {

    // Stato per i pazienti
    private val _patients = MutableStateFlow<List<Patient>>(emptyList())
    val patients: StateFlow<List<Patient>> = _patients

    // Stato per gli errori
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    init {
        loadPatients()
    }

    // Carica tutti i pazienti
    private fun loadPatients() {
        viewModelScope.launch {
            try {
                patientRepository.getAllPatients().collect { patientsList ->
                    _patients.value = patientsList
                }
            } catch (e: Exception) {
                _errorState.value = "Errore durante il caricamento dei pazienti: ${e.message}"
            }
        }
    }

    // Aggiunge un nuovo paziente
    fun addPatient(patient: Patient) {
        viewModelScope.launch {
            try {
                patientRepository.addPatient(patient)
            } catch (e: Exception) {
                _errorState.value = "Errore durante l'aggiunta del paziente: ${e.message}"
            }
        }
    }

    // Aggiorna un paziente
    fun updatePatient(patient: Patient) {
        viewModelScope.launch {
            try {
                patientRepository.updatePatient(patient)
            } catch (e: Exception) {
                _errorState.value = "Errore durante l'aggiornamento del paziente: ${e.message}"
            }
        }
    }

    // Elimina un paziente
    fun deletePatient(patient: Patient) {
        viewModelScope.launch {
            try {
                patientRepository.deletePatient(patient)
            } catch (e: Exception) {
                _errorState.value = "Errore durante l'eliminazione del paziente: ${e.message}"
            }
        }
    }
}
