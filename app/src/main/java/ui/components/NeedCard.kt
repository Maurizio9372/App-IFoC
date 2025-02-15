package com.example.appifoc.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import entities.Need.NeedEntity
import entities.Need.NeedStatus
import com.example.appifoc.viewmodel.NeedViewModel

@Composable
fun NeedCard(need: NeedEntity, needViewModel: NeedViewModel) {
    // Card per rappresentare un bisogno
    Card(modifier = Modifier.padding(bottom = 8.dp).fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Mostra la descrizione del bisogno
            Text(text = "Descrizione: ${need.description}")

            // Mostra il tipo del bisogno
            Text(text = "Tipo: ${need.type}")

            // Mostra lo stato del bisogno
            Text(text = "Stato: ${need.status}")

            // Bottone per cambiare lo stato del bisogno
            Button(onClick = {
                // Cambia lo stato del bisogno
                val newStatus = when (need.status) {
                    NeedStatus.OPEN -> NeedStatus.IN_PROGRESS
                    NeedStatus.IN_PROGRESS -> NeedStatus.COMPLETED
                    else -> NeedStatus.OPEN
                }

                // Aggiorna lo stato del bisogno tramite il ViewModel
                needViewModel.updateNeedStatus(need.id, newStatus)

                // Mostra un messaggio di conferma
                Toast.makeText(LocalContext.current, "Stato cambiato a $newStatus", Toast.LENGTH_SHORT).show()
            }) {
                Text("Cambia Stato")
            }
        }
    }
}
