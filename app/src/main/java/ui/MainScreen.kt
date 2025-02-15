package com.example.appifoc.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.appifoc.ui.theme.AppiFocTheme
import com.example.appifoc.viewmodel.NeedViewModel
import com.example.appifoc.ui.components.NeedCard

class MainScreen : ComponentActivity() {
    private val needViewModel: NeedViewModel by viewModels() // ViewModel per la gestione dei bisogni

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppiFocTheme {
                MainScreenUI(needViewModel) // Inizializza la UI passando il ViewModel
            }
        }
    }
}

@Composable
fun MainScreenUI(needViewModel: NeedViewModel) {
    // Raccoglie lo stato della lista di bisogni dal ViewModel
    val needs by needViewModel.needs.collectAsStateWithLifecycle(emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Lista dei Bisogni", style = MaterialTheme.typography.headlineSmall)

        // Ciclo per visualizzare ogni bisogno nella lista
        needs.forEach { need ->
            NeedCard(need = need, needViewModel = needViewModel)  // Usa NeedCard per visualizzare ciascun bisogno
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Bottone per aggiungere un nuovo bisogno
        Button(onClick = {
            val newNeed = NeedEntity(
                description = "Nuovo bisogno aggiunto",
                type = NeedStatus.OPEN,  // Tipo di bisogno
                status = "In corso" // Stato del bisogno
            )
            needViewModel.addNeed(newNeed) // Aggiungi il bisogno tramite il ViewModel
        }) {
            Text("Aggiungi Bisogno")
        }
    }
}
