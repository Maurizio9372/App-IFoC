package com.example.appifoc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appifoc.repository.NeedRepository

class NeedViewModelFactory(private val repository: NeedRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NeedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NeedViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
