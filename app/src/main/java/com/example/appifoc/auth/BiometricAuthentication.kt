package com.example.appifoc.ui.theme

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

object BiometricAuthentication {

    /**
     * Verifica se il dispositivo supporta l'autenticazione biometrica.
     * @param context Il contesto dell'applicazione.
     * @return true se l'autenticazione biometrica è disponibile, false altrimenti.
     */
    fun isBiometricAvailable(context: Context): Boolean {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate() == BiometricManager.BIOMETRIC_SUCCESS
    }

    /**
     * Esegue il prompt biometrico per l'autenticazione.
     * @param context Il contesto dell'applicazione.
     * @param onSuccess Funzione da chiamare se l'autenticazione è riuscita.
     * @param onFailure Funzione da chiamare se l'autenticazione è fallita.
     */
    fun authenticate(context: Context, onSuccess: () -> Unit, onFailure: () -> Unit) {
        // Controlla se il dispositivo supporta l'autenticazione biometrica
        if (!isBiometricAvailable(context)) {
            onFailure()  // Se non disponibile, invoca il callback di fallimento
            return
        }

        // Crea un executor per eseguire il codice nel thread principale
        val executor: Executor = ContextCompat.getMainExecutor(context)

        // Crea il BiometricPrompt per gestire l'autenticazione
        val biometricPrompt = BiometricPrompt(
            context as ComponentActivity,  // Assicurati che il contesto sia un'istanza di ComponentActivity
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()  // Invoca il callback di successo
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFailure()  // Invoca il callback di fallimento
                }
            })

        // Crea le informazioni da mostrare nel prompt biometrico
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticazione Biometrica")  // Titolo del prompt
            .setSubtitle("Usa la tua impronta digitale per accedere")  // Sottotitolo
            .setNegativeButtonText("Annulla")  // Testo per il bottone di annullamento
            .build()

        // Mostra il prompt di autenticazione biometrica
        biometricPrompt.authenticate(promptInfo)
    }
}
