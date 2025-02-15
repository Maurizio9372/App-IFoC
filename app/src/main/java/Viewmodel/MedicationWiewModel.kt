package com.example.appifoc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appifoc.repository.MedicationRepository
import entities.Medication.Medication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MedicationViewModel(application: Application, private val repository: MedicationRepository) : AndroidViewModel(application) {

    private val _medications = MutableStateFlow<List<Medication>>(emptyList())
    val medications: StateFlow<List<Medication>> = _medications

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    init {
        loadMedications()
    }

    private fun loadMedications() {
        viewModelScope.launch {
            try {
                repository.getAllMedications().collect { medicationsList ->
                    _medications.value = medicationsList
                }
            } catch (e: Exception) {
                _errorState.value = "Errore caricamento farmaci: ${e.message}"
            }
        }
    }

    fun addMedication(medication: Medication) {
        viewModelScope.launch {
            try {
                repository.addMedication(medication)
                loadMedications() // Ricarica i farmaci
            } catch (e: Exception) {
                _errorState.value = "Errore aggiunta farmaco: ${e.message}"
            }
        }
    }

    fun updateMedication(medication: Medication) {
        viewModelScope.launch {
            try {
                repository.updateMedication(medication)
                loadMedications() // Ricarica i farmaci
            } catch (e: Exception) {
                _errorState.value = "Errore aggiornamento farmaco: ${e.message}"
            }
        }
    }

    fun deleteMedication(medication: Medication) {
        viewModelScope.launch {
            try {
                repository.deleteMedication(medication)
                loadMedications() // Ricarica i farmaci
            } catch (e: Exception) {
                _errorState.value = "Errore eliminazione farmaco: ${e.message}"
            }
        }
    }
}
