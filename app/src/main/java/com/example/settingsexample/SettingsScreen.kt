package com.example.settingsexample

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("Settings") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MySpacer(size = 30)
            SettingsHeader()
            MySpacer(size = 20)
            SettingsBody()
        }
    }
}

@Composable
fun SettingsBody() {
    Card(
        modifier = Modifier.padding(20.dp)
    ) {
        CardItem(
            icon = R.drawable.ic_dark,
            text = "Dark mode",
            actionIcon = ActionIcon.SWITCH
        )
        Divider(thickness = 1.dp, color = Color.Black)
        CardItem(
            icon = R.drawable.ic_notification,
            text = "Notifications",
            actionIcon = ActionIcon.ARROW
        )
        Divider(thickness = 1.dp, color = Color.Black)
        CardItem(
            icon = R.drawable.ic_help,
            text = "Help and FAQ",
            actionIcon = ActionIcon.ARROW
        )
    }
}

@Composable
fun CardItem(icon: Int, text: String, actionIcon: ActionIcon) {
    var isOn by remember { mutableStateOf(true) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                if (ActionIcon.SWITCH == actionIcon) {
                    isOn = !isOn
                }
            }
            .padding(start = 16.dp, end = 20.dp, top = 10.dp, bottom = 12.dp)
    ) {
        Icon(painter = painterResource(id = icon), contentDescription = "")
        MySpacer(size = 10)
        Text(text = text)
        Spacer(modifier = Modifier.weight(1f))
        when (actionIcon) {

            ActionIcon.SWITCH -> Switch(
                checked = isOn,
                onCheckedChange = { isOn = !isOn },
                modifier = Modifier.size(30.dp)
            )

            ActionIcon.ARROW -> Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Composable
fun SettingsHeader() {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "",
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Black, CircleShape),
        contentScale = ContentScale.Fit
    )
    MySpacer(size = 10)
    Text(text = "user462157", style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun MySpacer(size: Int) {
    Spacer(modifier = Modifier.size(size.dp))
}