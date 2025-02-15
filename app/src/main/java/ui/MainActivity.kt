package com.example.appifoc.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.appifoc.ui.theme.AppiFocTheme
import com.example.appifoc.viewmodel.NeedViewModel
import entities.Need.NeedEntity
import com.example.appifoc.classifier.NeedType
import entities.Need.NeedStatus
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete

class MainActivity : ComponentActivity() {

    private val needViewModel: NeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppiFocTheme {
                MainScreen(needViewModel)
            }
        }

        // Trova la root view per il Snackbar
        val rootView = findViewById<View>(android.R.id.content)

        // Osserva gli errori
        needViewModel.errorState.observe(this, { errorMessage ->
            errorMessage?.let {
                // Mostra un Snackbar con il messaggio di errore
                Snackbar.make(rootView, it, Snackbar.LENGTH_LONG).show()
            }
        })
    }
}

@Composable
fun MainScreen(needViewModel: NeedViewModel) {
    val needs by needViewModel.needs.collectAsStateWithLifecycle(emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Lista dei Bisogni", style = MaterialTheme.typography.headlineSmall)

        needs.forEach { need ->
            NeedCard(need = need, needViewModel = needViewModel)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            // Simula l'aggiunta di un nuovo bisogno
            val newNeed = NeedEntity(
                patientId = 1,
                description = "Nuovo bisogno aggiunto",
                type = NeedType.SANITARI,
                date = System.currentTimeMillis(),
                priority = "medium",
                status = NeedStatus.OPEN
            )
            needViewModel.addNeed(newNeed)

            // Simula un errore
            needViewModel.saveNeed(1L, "Errore test", NeedType.SANITARI, "Bassa")
        }) {
            Text("Aggiungi Bisogno")
        }
    }
}

@Composable
fun NeedCard(need: NeedEntity, needViewModel: NeedViewModel) {
    Card(modifier = Modifier
        .padding(bottom = 8.dp)
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Descrizione: ${need.description}")
            Text(text = "Tipo: ${need.type}")
            Text(text = "Stato: ${need.status}")

            // Bottone per rimuovere un bisogno
            IconButton(onClick = { needViewModel.deleteNeed(need) }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Rimuovi bisogno")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppiFocTheme {
        MainScreen(NeedViewModel()) // Simulazione di anteprima con un ViewModel vuoto
    }
}
