package com.example.appifoc.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.appifoc.classifier.NeedType

@Composable
fun NeedCategorySelection(onNeedClassified: (NeedType) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Seleziona il tipo di bisogno")

        Button(onClick = { onNeedClassified(NeedType.SANITARY) }, modifier = Modifier.fillMaxWidth()) {
            Text("BISOGNO SANITARIO")
        }

        Button(onClick = { onNeedClassified(NeedType.SOCIAL) }, modifier = Modifier.fillMaxWidth()) {
            Text("BISOGNO SOCIALE")
        }

        Button(onClick = { onNeedClassified(NeedType.EDUCATIONAL) }, modifier = Modifier.fillMaxWidth()) {
            Text("BISOGNO EDUCATIVO")
        }
    }
}
