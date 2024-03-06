package com.example.settingsexample.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.settingsexample.R
import com.example.settingsexample.ui.theme.SettingsExampleTheme

@Composable
fun AboutUsScreen(navController: NavHostController, darkMode: Boolean) {
    Scaffold(
        topBar = {
            MyTopAppBar(title = "About Us", navController = navController, darkMode = darkMode)
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            Image(
                painter = painterResource(id = R.drawable.happy),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    navController: NavController,
    darkMode: Boolean
) {
    SettingsExampleTheme(darkTheme = darkMode) {
        CenterAlignedTopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable { navController.popBackStack() }
                        .padding(start = 10.dp)
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.LightGray,
            )
        )
    }

}