package com.example.settingsexample.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.settingsexample.R

@Composable
fun AboutUsScreen(navController: NavHostController) {
    Image(
        painter = painterResource(id = R.drawable.happy),
        contentDescription = "",
        modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp)
    )
}