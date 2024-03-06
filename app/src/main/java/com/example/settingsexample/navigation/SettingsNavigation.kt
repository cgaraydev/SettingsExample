package com.example.settingsexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.settingsexample.screens.AboutUsScreen
import com.example.settingsexample.screens.ReportABugScreen
import com.example.settingsexample.screens.SettingsScreen

@Composable
fun SettingsNavigation(darkMode: Boolean) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreensList.SettingsScreen.name
    ) {
        composable(ScreensList.SettingsScreen.name) {
            SettingsScreen(navController)
        }
        composable(ScreensList.AboutUsScreen.name) {
            AboutUsScreen(navController, darkMode)
        }
        composable(ScreensList.ReportABugScreen.name){
            ReportABugScreen(navController, darkMode)
        }
    }
}