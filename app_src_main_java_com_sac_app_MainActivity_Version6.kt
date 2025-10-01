package com.sac.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sac.app.ui.SACTheme
import androidx.compose.material3.*
import androidx.navigation.compose.*
import com.sac.app.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SACTheme {
                SACApp()
            }
        }
    }
}

@Composable
fun SACApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { NavMenu(navController) }
    ) { innerPadding ->
        NavHost(navController, startDestination = "pin", modifier = Modifier.padding(innerPadding)) {
            composable("pin") { PinScreen(navController) }
            composable("journal") { JournalScreen(navController) }
            composable("addEntry") { AddEntryScreen(navController) }
            composable("stats") { StatsScreen(navController) }
            composable("harm") { HarmReductionScreen(navController) }
            composable("news") { NewsScreen(navController) }
            composable("export") { ExportScreen(navController) }
            composable("settings") { SettingsScreen(navController) }
        }
    }
}