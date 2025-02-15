package com.example.appifoc // Assicurati che il package corrisponda al tuo progetto

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Abilita Hilt per la Dependency Injection
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Inizializzazione globale, se necessario
    }
}
