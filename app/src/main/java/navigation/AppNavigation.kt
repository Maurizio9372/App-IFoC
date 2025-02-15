package com.example.appifoc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appifoc.ui.screens.*

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home",  // Definisci la schermata di partenza
        modifier = modifier
    ) {
        composable("home") { HomeScreen(navController) }
        composable("sanitario") { SanitarioScreen(navController) }
        composable("sociale") { SocialeScreen(navController) }
        composable("educativo") { EducativoScreen(navController) }
        composable("terapia") { TerapiaScreen() }
        composable("isolamento") { IsolamentoScreen() }
        composable("educazione_caregiver") { EducazioneCaregiverScreen() }
    }
}
