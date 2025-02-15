package com.example.appifoc.classifier

// Enum per rappresentare i tipi di bisogni
enum class NeedType {
    SANITARY, SOCIAL, EDUCATIONAL
}

// Classe per la classificazione dei bisogni
class NeedClassifier {

    // Funzione che classifica un bisogno in una delle tre categorie
    fun classifyNeed(description: String): NeedType {
        return when {
            description.contains("farmaco", ignoreCase = true) ||
                    description.contains("medico", ignoreCase = true) ||
                    description.contains("visita", ignoreCase = true) -> NeedType.SANITARY

            description.contains("familiare", ignoreCase = true) ||
                    description.contains("sociale", ignoreCase = true) ||
                    description.contains("supporto", ignoreCase = true) -> NeedType.SOCIAL

            description.contains("educazione", ignoreCase = true) ||
                    description.contains("informazioni", ignoreCase = true) ||
                    description.contains("formazione", ignoreCase = true) -> NeedType.EDUCATIONAL

            else -> NeedType.SOCIAL // Default, nel caso non venga classificato
        }
    }
}
