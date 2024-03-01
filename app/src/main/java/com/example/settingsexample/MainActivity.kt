package com.example.settingsexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.settingsexample.navigation.SettingsNavigation
import com.example.settingsexample.screens.ReportABugScreen
import com.example.settingsexample.screens.SettingsScreen
import com.example.settingsexample.ui.theme.SettingsExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SettingsExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReportABugScreen()
//                    SettingsNavigation()
                }
            }
        }
    }
}

