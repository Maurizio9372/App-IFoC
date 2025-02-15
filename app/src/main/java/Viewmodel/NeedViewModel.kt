package com.example.appifoc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appifoc.repository.NeedRepository
import entities.Need.NeedEntity
import entities.Need.NeedStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NeedViewModel(application: Application, private val repository: NeedRepository) : AndroidViewModel(application) {

    // Stato per i bisogni
    private val _needs = MutableStateFlow<List<NeedEntity>>(emptyList())
    val needs: StateFlow<List<NeedEntity>> = _needs

    // Stato per gli errori
    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    init {
        loadNeeds()
    }

    // Carica i bisogni dal database
    private fun loadNeeds() {
        viewModelScope.launch {
            try {
                repository.getAllNeeds().collect { needsList ->
                    _needs.value = needsList
                }
            } catch (e: Exception) {
                _errorState.value = "Errore durante il caricamento dei bisogni: ${e.message}"
            }
        }
    }

    // Aggiunge un nuovo bisogno
    fun addNeed(patientId: Long, description: String, type: NeedEntity.NeedType, priority: String) {
        viewModelScope.launch {
            try {
                repository.saveNeed(patientId, description, type, priority)
            } catch (e: Exception) {
                _errorState.value = "Errore durante il salvataggio del bisogno: ${e.message}"
            }
        }
    }

    // Aggiorna lo stato di un bisogno esistente
    fun updateNeedStatus(id: Long, newStatus: NeedStatus) {
        viewModelScope.launch {
            try {
                repository.updateNeedStatus(id, newStatus)
            } catch (e: Exception) {
                _errorState.value = "Errore durante l'aggiornamento dello stato: ${e.message}"
            }
        }
    }

    // Elimina un bisogno
    fun deleteNeed(need: NeedEntity) {
        viewModelScope.launch {
            try {
                repository.deleteNeed(need)
            } catch (e: Exception) {
                _errorState.value = "Errore durante l'eliminazione del bisogno: ${e.message}"
            }
        }
    }
}
